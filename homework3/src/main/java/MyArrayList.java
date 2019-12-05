import java.util.Iterator;

public class MyArrayList <T> implements Iterable<T>{

        private T[] array;
        private int length;

        public MyArrayList(T[] array) {
            this.array = array;
            this.length = array.length;
        }

        @Override
        public Iterator<T> iterator() {
            Iterator<T> it = new Iterator<T>() {

                private int currentIndex = 0;

                @Override
                public boolean hasNext() {
                    return currentIndex < length && array[currentIndex] != null;
                }

                @Override
                public T next() {
                    return array[currentIndex++];
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };

            return it;
        }

    public Iterator<T> iteratorReverse() {
        Iterator<T> it = new Iterator<T>() {

            private int currentIndex = length - 1;

            @Override
            public boolean hasNext() {
                return currentIndex >= 0 && array[currentIndex] != null;
            }

            @Override
            public T next() {
                return array[currentIndex--];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

        return it;
    }
}
