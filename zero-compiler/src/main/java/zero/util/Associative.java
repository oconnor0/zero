/* Copyright (C) 2012 Matt O'Connor <thegreendragon@gmail.com> */
package zero.util;

public interface Associative<K, V> {
  Associative add(K k, V v);
  V get(K k);
}
