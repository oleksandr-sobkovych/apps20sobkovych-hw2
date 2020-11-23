package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.collections.exceptions.EmptyListException;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    private ImmutableList emptyList;
    private ImmutableList list;

    @Before
    public void setUp() {
        this.emptyList = new ImmutableLinkedList();
        this.list = new ImmutableLinkedList(1, "hello_world", 'a');
    }

    @Test
    public void testEmptyTrue() {
        assertTrue(this.emptyList.isEmpty());
    }

    @Test
    public void testEmptyFalse() {
        assertFalse(this.list.isEmpty());
    }

    @Test
    public void testEmptyTrueToFalse() {
        assertFalse(this.emptyList.add(1).isEmpty());
    }

    @Test
    public void testEmptyConstructor() {
        assertTrue(new ImmutableLinkedList().isEmpty());
    }

    @Test
    public void testEmptySize() {
        assertEquals(0, this.emptyList.size());
    }

    @Test
    public void testSize() {
        assertEquals(3, this.list.size());
    }

    @Test
    public void testSizeAdd() {
        assertEquals(4, this.list.add(4).size());
    }

    @Test
    public void testSizeRemove() {
        assertEquals(2, this.list.remove(1).size());
    }

    @Test
    public void testSizeAddAll() {
        assertEquals(6, this.list.addAll(1, new Object[]{4, "hello", 6}).size());
    }

    @Test
    public void testClear() {
        assertTrue(this.list.clear().isEmpty());
    }

    @Test
    public void testIndexOfFound() {
        assertEquals(1, this.list.indexOf("hello_world"));
    }

    @Test
    public void testIndexOfNotFound() {
        assertEquals(-1, this.list.indexOf(10));
    }

    @Test
    public void testIndexOfEdgeCases() {
        assertEquals(0, this.list.indexOf(1));
        assertEquals(2, this.list.indexOf('a'));
    }

    @Test
    public void testToArray() {
        assertArrayEquals(new Object[]{1, "hello_world", 'a'}, this.list.toArray());
    }

    @Test
    public void testToArrayEmpty() {
        assertArrayEquals(new Object[]{}, this.emptyList.toArray());
    }

    @Test
    public void testToString() {
        ImmutableList tempList = new ImmutableLinkedList(1, 2.3, 'a');
        assertEquals("1, 2.3, a", tempList.toString());
    }

    @Test
    public void testToStringEmpty() {
        assertEquals("", this.emptyList.toString());
    }

    @Test
    public void testGet() {
        assertEquals("hello_world", this.list.get(1));
    }

    @Test
    public void testGetEdgeCases() {
        assertEquals(1, this.list.get(0));
        assertEquals('a', this.list.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexOutOfBounds() {
        this.list.get(3);
    }

    @Test(expected = EmptyListException.class)
    public void testGetEmpty() {
        this.emptyList.get(0);
    }

    @Test
    public void testSet() {
        String expected = "bye_world";
        int index = 1;
        this.list = this.list.set(index, expected);
        assertEquals(expected, this.list.get(index));
    }

    @Test
    public void testSetEdgeCases() {
        int expected1 = 100;
        char expected2 = 'f';
        int i = 0, j = 2;
        this.list = this.list.set(i, expected1);
        this.list = this.list.set(j, expected2);
        assertEquals(expected1, this.list.get(i));
        assertEquals(expected2, this.list.get(j));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetIndexOutOfBounds() {
        this.list.set(-2, 10);
    }

    @Test(expected = EmptyListException.class)
    public void testSetEmpty() {
        this.emptyList.set(-2, 10);
    }

    @Test
    public void testRemoveSize() {
        this.list = this.list.remove(1);
        assertEquals(2, this.list.size());
    }

    @Test
    public void testRemove() {
        this.list = this.list.remove(1);
        assertEquals('a', this.list.get(1));
    }

    @Test
    public void testRemoveEdgeCaseMin() {
        this.list = this.list.remove(0);
        assertArrayEquals(new Object[]{"hello_world", 'a'}, this.list.toArray());
    }

    @Test
    public void testRemoveEdgeCaseMax() {
        this.list = this.list.remove(2);
        assertArrayEquals(new Object[]{1, "hello_world"}, this.list.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexOutOfBounds() {
        this.list.remove(123);
    }

    @Test(expected = EmptyListException.class)
    public void testRemoveEmpty() {
        this.emptyList.remove(12);
    }

    @Test
    public void testAddAll() {
        this.list = this.list.addAll(1, new Object[]{4, 'b'});
        assertArrayEquals(new Object[]{1, 4, 'b', "hello_world", 'a'}, this.list.toArray());
    }

    @Test
    public void testAddAllToEnd() {
        this.list = this.list.addAll(3, new Object[]{4, 'b'});
        assertArrayEquals(new Object[]{1, "hello_world", 'a', 4, 'b'}, this.list.toArray());
    }

    @Test
    public void testAddAllToStart() {
        this.list = this.list.addAll(0, new Object[]{4, 'b'});
        assertArrayEquals(new Object[]{4, 'b', 1, "hello_world", 'a'}, this.list.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllIndexOutOfBounds() {
        this.list.addAll(-1, new Object[]{4, 'b'});
    }

    @Test
    public void testAddAllEmptyArray() {
        assertArrayEquals(this.list.toArray(), this.list.addAll(1, new Object[0]).toArray());
    }

    @Test
    public void testAddAllToEmpty() {
        assertArrayEquals(new Object[]{4, 'b'}, this.emptyList.addAll(0, new Object[]{4, 'b'}).toArray());
    }

    @Test
    public void testAddAllWithNoIndex() {
        this.list = this.list.addAll(new Object[]{4, 'b'});
        assertArrayEquals(new Object[]{1, "hello_world", 'a', 4, 'b'}, this.list.toArray());
    }

    @Test
    public void testAdd() {
        this.list = this.list.add(1, 4);
        assertArrayEquals(new Object[]{1, 4, "hello_world", 'a'}, this.list.toArray());
    }

    @Test
    public void testAddWithoutIndex() {
        this.list = this.list.add('b');
        assertArrayEquals(new Object[]{1, "hello_world", 'a', 'b'}, this.list.toArray());
    }

    @Test
    public void testAddFirst() {
        ImmutableLinkedList list1 = new ImmutableLinkedList(1, 2, 3);
        assertArrayEquals(new Object[]{4, 1, 2, 3}, list1.addFirst(4).toArray());
    }

    @Test
    public void testAddLast() {
        ImmutableLinkedList list1 = new ImmutableLinkedList(1, 2, 3);
        assertArrayEquals(new Object[]{1, 2, 3, 4}, list1.addLast(4).toArray());
    }

    @Test
    public void testGetLast() {
        ImmutableLinkedList list1 = new ImmutableLinkedList(1, 2, 3);
        assertEquals(3, list1.getLast());
    }

    @Test
    public void testGetFirst() {
        ImmutableLinkedList list1 = new ImmutableLinkedList(1, 2, 3);
        assertEquals(1, list1.getFirst());
    }

    @Test
    public void testRemoveFirst() {
        ImmutableLinkedList list1 = new ImmutableLinkedList(1, 2, 3);
        assertEquals(2, list1.removeFirst().get(0));
    }

    @Test
    public void testRemoveLast() {
        ImmutableLinkedList list1 = new ImmutableLinkedList(1, 2, 3);
        assertArrayEquals(new Object[]{1, 2}, list1.removeLast().toArray());
    }
}
