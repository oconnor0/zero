/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.util;

public class AssociativeList<K, V> implements Associative<K, V> {
  private final K k;
  private final V v;
  private final Associative<K, V> tl;

  public AssociativeList(final K k, final V v, final Associative<K, V> tl) {
    this.k = k;
    this.v = v;
    this.tl = tl;
  }

  public Associative add(K k, V v) {
    return new AssociativeList<>(k, v, this);
  }

  public V get(K k) {
    if(k.equals(this.k)) {
      return v;
    } else {
      return tl.get(k);
    }
  }
}
