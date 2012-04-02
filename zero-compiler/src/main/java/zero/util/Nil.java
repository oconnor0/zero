/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.util;

public class Nil<K, V> implements Associative<K, V> {
  private static final Associative<?, ?> NIL = new Nil<>();
  private Nil() {}

  public static <K, V> Associative<K, V> get() {
    return (Associative<K, V>) NIL;
  }

  public Associative add(K k, V v) {
    return new AssociativeList<>(k, v, this);
  }

  public V get(K k) {
    return null;
  }
}
