/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.util;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 *
 * @author jonathan
 * @param <T>
 */
public class ArrayMap<T, U>  implements Map<T, U> {

    private final List<U> values;
    private final  List<T> keys;

    public ArrayMap() {
        values = new ArrayList<>();
        keys = new ArrayList<>();
    }
      public ArrayMap(int capacity) {
        values = new ArrayList<>(capacity);
        keys = new ArrayList<>(capacity);
    }
    
    

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean isEmpty() {
        return keys.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return keys.contains((T) key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values.contains((U) value);
    }

    @Override
    public U get(Object key) {
        int indexOf = keys.indexOf(key);
        if (indexOf != -1) {
            return values.get(indexOf);
        } else {
            return null;
        }
    }

    @Override
    public U put(T key, U value) {
        int indexOf = keys.indexOf(key);
        if (indexOf == -1) {
            keys.add(key);
            values.add(value);
        } else {
            values.set(indexOf, value);
        }
        return value;
    }

    @Override
    public U remove(Object key) {
        int indexOf = keys.indexOf(key);
        if (indexOf == -1) {
            return null;
        } else {
            U remove = values.remove(indexOf);
            keys.remove(indexOf);
            return remove;
        }
    }

    @Override
    public void putAll(Map<? extends T, ? extends U> m) {
        for (Map.Entry<? extends T, ? extends U> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public void clear() {
        values.clear();
        keys.clear();
    }

    @Override
    public Set<T> keySet() {
        return new AbstractSet<T>() {
            
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    private Iterator<Entry<T, U>> i = entrySet().iterator();

                    public boolean hasNext() {
                        return i.hasNext();
                    }

                    public T next() {
                        return i.next().getKey();
                    }

                    public void remove() {
                        i.remove();
                    }
                    
                };
            }

            public int size() {
                return ArrayMap.this.size();
            }

            public boolean isEmpty() {
                return ArrayMap.this.isEmpty();
            }

            public void clear() {
                ArrayMap.this.clear();
            }

            public boolean contains(Object k) {
                return ArrayMap.this.containsKey((T)k);
            }
        };
    }

    public Collection<U> values() {
        return new AbstractCollection<U>() {
            public Iterator<U> iterator() {
                return new Iterator<U>() {
                    private Iterator<Entry<T, U>> i = entrySet().iterator();

                    public boolean hasNext() {
                        return i.hasNext();
                    }

                    public U next() {
                        return i.next().getValue();
                    }

                    public void remove() {
                        i.remove();
                    }
                };
            }

            public int size() {
                return ArrayMap.this.size();
            }

            public boolean isEmpty() {
                return ArrayMap.this.isEmpty();
            }

            public void clear() {
                ArrayMap.this.clear();
            }

            public boolean contains(Object v) {
                return ArrayMap.this.containsValue(v);
            }
        };
    }

    @Override
    public Set<Entry<T, U>> entrySet() {
        return new AbstractSet<Map.Entry<T, U>>() {

            @Override
            public Iterator<Entry<T, U>> iterator() {
                return new Iterator<Entry<T, U>>() {
                    private int i = 0;
                    private Entry<T, U> next = null;

                    @Override
                    public boolean hasNext() {
                        return i < ArrayMap.this.size() ;
                    }

                    @Override
                    public Entry<T, U> next() {
                        
                        if (i > ArrayMap.this.size() - 1) {
                            throw new NoSuchElementException();
                        } else {
                            next = new Entry<T, U>() {
                                private final int j = i;

                                @Override
                                public T getKey() {
                                    return ArrayMap.this.keys.get(j);
                                }

                                @Override
                                public U getValue() {
                                    return ArrayMap.this.values.get(j);
                                }

                                @Override
                                public U setValue(U value) {
                                    return ArrayMap.this.values.set(j, value);
                                }
                                
                                public String toString(){
                                    return "k="+getKey()+", v="+getValue();
                                }
                            };
                            i++;
                            return next;
                        }
                    }

                    @Override
                    public void remove() {
                        ArrayMap.this.remove(next.getKey());
                        i--;
                    }
                };
            }

            @Override
            public int size() {
                return ArrayMap.this.size();
            }
        };
    }
    
    public static void main(String[] args){
        Map<String, String> m = new ArrayMap<String, String>();
//         System.out.println( m.put("a", "coucou"));
//        System.out.println(m.put("a", "coucsou"));
//        System.out.println(m.put("a", "coucdou"));
//        
//        System.out.println(m.get("a"));
//        
//         System.out.println(m.remove("a"));
//         
//         System.out.println(m.size());
          System.out.println( m.put("a", "coucou"));
        System.out.println(m.put("b", "d"));
        System.out.println(m.put("c", "coucdou"));
        Set<Entry<String, String>> entrySet = m.entrySet();
        for(Entry<String, String> e : entrySet){
            System.out.println(e);
            System.out.println(m.containsKey(e.getKey()));
            System.out.println(m.containsValue(e.getValue()));
        }
        Iterator<String> iterator = m.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();   
        }
        System.out.println(m.size());
         for(Entry<String, String> e : entrySet){
            System.out.println(e);
//            System.out.println(m.containsKey(e.getKey()));
//            System.out.println(m.containsValue(e.getValue()));
        }
        
        
        
    }
}
