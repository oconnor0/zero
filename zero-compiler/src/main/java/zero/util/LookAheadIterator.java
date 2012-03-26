/* (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.util;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LookAheadIterator<E> implements Iterator<E> {
  private final List<E> lookAhead = new LinkedList<>();
  private final Iterator<E> wrapped;
  private final E beyond;

  public LookAheadIterator(final Iterator<E> wrapped) {
    this(wrapped, null);
  }

  public LookAheadIterator(final Iterator<E> wrapped, final E beyond) {
    this.wrapped = wrapped;
    this.beyond  = beyond;
  }

  @Override
  public boolean hasNext() {
    return lookAhead.size() > 0 || wrapped.hasNext();
  }

  /** Throws NoSuchElementException if beyond is null; otherwise returns beyond. */
  @Override
  public E next() {
    if(lookAhead.size() > 0) {
      return lookAhead.remove(0);
    } else if(wrapped.hasNext()) {
      return wrapped.next();
    } else {
      return empty();
    }
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  public E lookAhead(final int distance) {
    for(int i = 0; i <= distance && wrapped.hasNext(); i++) {
      lookAhead.add(wrapped.next());
    }
    if(lookAhead.size() > distance) {
      return lookAhead.get(distance);
    } else {
      return empty();
    }
  }

  private final E empty() {
    if(beyond == null) {
      throw new NoSuchElementException();
    } else {
      return beyond;
    }
  }
}