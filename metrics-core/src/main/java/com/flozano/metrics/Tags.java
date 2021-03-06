package com.flozano.metrics;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

public final class Tags {
	private static final Tags EMPTY = new Tags(Collections.emptySortedSet());
	private final SortedSet<Tag> tags;

	public static Tags empty() {
		return EMPTY;
	}

	private Tags(SortedSet<Tag> tags) {
		this.tags = tags;
	}

	public final Tags with(CharSequence name, CharSequence value) {
		SortedSet<Tag> t = new TreeSet<Tag>(tags);
		t.add(new Tag(name, value));
		return new Tags(t);
	}

	public final Stream<Tag> stream() {
		return tags.stream();
	}

	public boolean isEmpty() {
		return tags.isEmpty();
	}

	public static final class Tag implements Comparable<Tag> {
		public final CharSequence name;
		public final CharSequence value;

		public Tag(CharSequence name, CharSequence value) {
			super();
			this.name = requireNonNull(name);
			this.value = requireNonNull(value);
		}

		@Override
		public int compareTo(Tag o) {
			if (o == null) {
				return 1;
			} else {
				int result = name.toString().compareTo(o.name.toString());
				if (result == 0) {
					return value.toString().compareTo(o.value.toString());
				} else {
					return result;
				}
			}
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Tag other = (Tag) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.toString().equals(other.name.toString()))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.toString().equals(other.value.toString()))
				return false;
			return true;
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tags other = (Tags) obj;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

	public boolean has(CharSequence name) {
		return tags.stream().anyMatch(x -> x.name.toString().equals(name.toString()));
	}
}
