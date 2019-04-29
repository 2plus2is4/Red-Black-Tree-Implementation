package eg.edu.alexu.csd.filestructure.redblacktree;


import eg.edu.alexu.csd.filestructure.redblacktree.INode;
import eg.edu.alexu.csd.filestructure.redblacktree.IRedBlackTree;
import eg.edu.alexu.csd.filestructure.redblacktree.ITreeMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.management.RuntimeErrorException;

import org.junit.Assert;
import org.junit.Test;

public class UnitTest {
    private final boolean debug = false;

    @Test
    public void testRootNull() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        INode root = null;
        try {
            root = redBlackTree.getRoot();
            boolean check = false;
            if (root == null) {
                check = true;
            }
            if (!check) {
                Assert.assertEquals((Object) true, (Object) root.isNull());
            }
        } catch (RuntimeErrorException check) {
        } catch (Throwable e) {
            TestRunner.fail("Fail to getRoot of tree", e);
        }
    }

    @Test
    public void testGetRoot() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        INode root = null;
        try {
            redBlackTree.insert("Soso", "Toto");
            root = redBlackTree.getRoot();
            Assert.assertEquals((Object) "Soso", root.getKey());
            Assert.assertEquals((Object) "Toto", root.getValue());
        } catch (Throwable e) {
            TestRunner.fail("Fail to getRoot of tree", e);
        }
    }

    @Test
    public void testIsEmptyTrue() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            Assert.assertEquals((Object) true, (Object) redBlackTree.isEmpty());
        } catch (Throwable e) {
            TestRunner.fail("Fail to test if tree is Empty", e);
        }
    }

    @Test
    public void testIsEmptyFalse() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            redBlackTree.insert("soso", "toto");
            Assert.assertEquals((Object) false, (Object) redBlackTree.isEmpty());
        } catch (Throwable e) {
            TestRunner.fail("Fail to test if tree is Empty", e);
        }
    }

    @Test
    public void testClearTree() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            redBlackTree.insert("soso", "toto");
            redBlackTree.clear();
            redBlackTree.clear();
            redBlackTree.insert("soso", "toto");
            redBlackTree.insert("toto", "toto");
            redBlackTree.insert("fofo", "toto");
            redBlackTree.insert("koko", "toto");
            Assert.assertEquals((Object) false, (Object) redBlackTree.isEmpty());
            redBlackTree.clear();
            Assert.assertEquals((Object) true, (Object) redBlackTree.isEmpty());
        } catch (Throwable e) {
            TestRunner.fail("Fail to clear the tree", e);
        }
    }

    @Test
    public void testNormalSearch() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            int i;
            Random r = new Random();
            ArrayList<Integer> keysToSearch = new ArrayList<Integer>();
            for (i = 0; i < 1000; ++i) {
                int key = r.nextInt(10000);
                if (i % 50 == 0) {
                    keysToSearch.add(key);
                }
                redBlackTree.insert(key, "toto" + key);
            }
            for (i = 0; i < keysToSearch.size(); ++i) {
                String ans = (String) redBlackTree.search((Integer) keysToSearch.get(i));
                Assert.assertEquals((Object) ("toto" + keysToSearch.get(i)), (Object) ans);
            }
        } catch (Throwable e) {
            TestRunner.fail("Fail to search for a key in the tree", e);
        }
    }

    @Test
    public void testSearchEmpty() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            Assert.assertNull(redBlackTree.search(123));
        } catch (Throwable e) {
            TestRunner.fail("Fail to search for a key in the tree", e);
        }
    }

    @Test
    public void testSearchAbsentKey() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            for (int i = 0; i < 10000; ++i) {
                redBlackTree.insert(i, "koko" + i);
            }
            Assert.assertNull(redBlackTree.search(100000));
        } catch (Throwable e) {
            TestRunner.fail("Fail to search for a key in the tree", e);
        }
    }

    @Test(timeout = 10000L)
    public void testStressSearch() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            int i;
            Random r = new Random();
            ArrayList<Integer> keysToSearch = new ArrayList<Integer>();
            for (i = 0; i < 10000000; ++i) {
                int key = r.nextInt(100000);
                if (i % 50 == 0) {
                    keysToSearch.add(key);
                }
                redBlackTree.insert(key, "toto" + key);
            }
            for (i = 0; i < keysToSearch.size(); ++i) {
                String ans = (String) redBlackTree.search((Integer) keysToSearch.get(i));
                Assert.assertEquals((Object) ("toto" + keysToSearch.get(i)), (Object) ans);
            }
        } catch (Throwable e) {
            TestRunner.fail("Fail to search for a key in the tree", e);
        }
    }

    @Test
    public void testSearchWithNull() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            Random r = new Random();
            for (int i = 0; i < 100; ++i) {
                int key = r.nextInt(100000);
                redBlackTree.insert(key, "toto" + key);
            }
            redBlackTree.search(null);
            Assert.fail();
        } catch (RuntimeErrorException r) {
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle search with null parameter", e);
        }
    }

    @Test
    public void testNormalContains() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            int i;
            Random r = new Random();
            ArrayList<Integer> keysToSearch = new ArrayList<Integer>();
            for (i = 0; i < 1000; ++i) {
                int key = r.nextInt(10000);
                if (i % 50 == 0) {
                    keysToSearch.add(key);
                }
                redBlackTree.insert(key, "toto" + key);
            }
            for (i = 0; i < keysToSearch.size(); ++i) {
                boolean ans = redBlackTree.contains((Integer) keysToSearch.get(i));
                Assert.assertEquals((Object) true, (Object) ans);
            }
        } catch (Throwable e) {
            TestRunner.fail("Fail contains a key in the tree", e);
        }
    }

    @Test
    public void testContainsWithNull() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            Random r = new Random();
            for (int i = 0; i < 100; ++i) {
                int key = r.nextInt(100000);
                redBlackTree.insert(key, "toto" + key);
            }
            redBlackTree.contains(null);
            Assert.fail();
        } catch (RuntimeErrorException r) {
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle contains with null parameter", e);
        }
    }

    @Test
    public void testContainsEmpty() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            Assert.assertEquals((Object) false, (Object) redBlackTree.contains(123));
        } catch (Throwable e) {
            TestRunner.fail("Fail contains a key in the tree", e);
        }
    }

    @Test
    public void testContainsAbsentKey() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            for (int i = 0; i < 10000; ++i) {
                redBlackTree.insert(i, "koko" + i);
            }
            Assert.assertEquals((Object) false, (Object) redBlackTree.contains(100000));
        } catch (Throwable e) {
            TestRunner.fail("Fail contains a key in the tree", e);
        }
    }

    @Test(timeout = 10000L)
    public void testStressContains() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            int i;
            Random r = new Random();
            ArrayList<Integer> keysToSearch = new ArrayList<Integer>();
            for (i = 0; i < 10000000; ++i) {
                int key = r.nextInt(100000);
                if (i % 50 == 0) {
                    keysToSearch.add(key);
                }
                redBlackTree.insert(key, "toto" + key);
            }
            for (i = 0; i < keysToSearch.size(); ++i) {
                boolean ans = redBlackTree.contains((Integer) keysToSearch.get(i));
                Assert.assertEquals((Object) true, (Object) ans);
            }
        } catch (Throwable e) {
            TestRunner.fail("Fail contains a key in the tree", e);
        }
    }

    @Test
    public void testInsertionWithNullKey() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            Random r = new Random();
            for (int i = 0; i < 100; ++i) {
                int key = r.nextInt(100000);
                redBlackTree.insert(key, "toto" + key);
            }
            redBlackTree.insert(null, "soso");
            Assert.fail();
        } catch (RuntimeErrorException r) {
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle search with null parameter", e);
        }
    }

    @Test
    public void testInsertionWithNullValue() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            Random r = new Random();
            for (int i = 0; i < 100; ++i) {
                int key = r.nextInt(100000);
                redBlackTree.insert(key, "toto" + key);
            }
            redBlackTree.insert(123, null);
            Assert.fail();
        } catch (RuntimeErrorException r) {
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle search with null parameter", e);
        }
    }

    @Test
    public void testNormalInsertionWithRandomData() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            Random r = new Random();
            for (int i = 0; i < 100; ++i) {
                int key = r.nextInt(1000);
                redBlackTree.insert(key, "toto" + key);
                System.out.println("\n"+i);
                Assert.assertTrue((boolean) this.verifyProps(redBlackTree.getRoot()));
            }
        } catch (Throwable e) {
            TestRunner.fail("Fail to insert a key in the tree", e);
        }
    }

    @Test
    public void testNormalInsertion() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            redBlackTree.insert(20, "soso");
            redBlackTree.insert(15, "soso");
            redBlackTree.insert(10, "soso");
            redBlackTree.insert(7, "soso");
            redBlackTree.insert(9, "soso");
            redBlackTree.insert(12, "soso");
            redBlackTree.insert(24, "soso");
            redBlackTree.insert(22, "soso");
            redBlackTree.insert(13, "soso");
            redBlackTree.insert(11, "soso");
            String expectedAns = "12B9R15R7B10B13B22BNBNBNB11RNBNB20R24RNBNBNBNBNBNB";
            String actualAns = this.levelOrder(redBlackTree.getRoot());
            Assert.assertEquals((Object) expectedAns, (Object) actualAns);
        } catch (Throwable e) {
            TestRunner.fail("Fail to insert a key in the tree", e);
        }
    }

    @Test
    public void testUpdateValue() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            redBlackTree.insert(20, "soso20");
            redBlackTree.insert(15, "soso15");
            redBlackTree.insert(10, "soso10");
            redBlackTree.insert(7, "soso7");
            redBlackTree.insert(9, "soso9");
            redBlackTree.insert(12, "soso12");
            redBlackTree.insert(24, "soso24");
            redBlackTree.insert(22, "soso22");
            redBlackTree.insert(13, "soso13");
            redBlackTree.insert(11, "soso11");
            Assert.assertEquals((Object) "soso13", redBlackTree.search(13));
            redBlackTree.insert(13, "koko13");
            Assert.assertEquals((Object) "koko13", redBlackTree.search(13));
        } catch (Throwable e) {
            TestRunner.fail("Fail to insert a key in the tree", e);
        }
    }

    @Test
    public void testDeleteWithNull() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            redBlackTree.delete(null);
            Assert.fail((String) "Fail to handle deletion with null parameter");
        } catch (RuntimeErrorException runtimeErrorException) {
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle deletion with null parameter", e);
        }
    }

    @Test
    public void testDeleteAllElementsInTree() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            Random r = new Random();
            HashSet<Integer> list = new HashSet<Integer>();
            for (int i = 0; i < 100000; ++i) {
                int key = r.nextInt(10000);
                list.add(key);
                redBlackTree.insert(key, "soso" + key);
            }
            for (Integer elem : list) {
                Assert.assertTrue((boolean) redBlackTree.delete(elem));
            }
            INode node = redBlackTree.getRoot();
            if (node != null && !node.isNull()) {
                Assert.fail();
            }
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle deletion", e);
        }
    }

    @Test
    public void testDeleteRandomElementsInTree() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            Random r = new Random();
            HashSet<Integer> list = new HashSet<Integer>();
            for (int i = 0; i < 100000; ++i) {
                int key = r.nextInt(10000);
                if (r.nextInt(5) % 4 == 0) {
                    list.add(key);
                }
                redBlackTree.insert(key, "soso" + key);
            }
            for (Integer elem : list) {
                Assert.assertTrue((boolean) redBlackTree.delete(elem));
            }
            INode<Integer, String> node = redBlackTree.getRoot();
            if (node == null || node.isNull()) {
                Assert.fail();
            }
            Assert.assertTrue((boolean) this.verifyProps(node));
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle deletion", e);
        }
    }

    @Test
    public void testDeleteWhileInsertingInTree() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            Random r = new Random();
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < 100000; ++i) {
                int key = r.nextInt(10000);
                redBlackTree.insert(key, "soso" + key);
                if (r.nextInt(5) % 4 != 0 || list.size() <= 0)
                    continue;
                int index = r.nextInt(list.size());
                redBlackTree.delete((Integer) list.get(index));
                list.remove(index);
                list.add(key);
            }
            Assert.assertTrue((boolean) this.verifyProps(redBlackTree.getRoot()));
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle deletion", e);
        }
    }

    @Test(timeout = 10000L)
    public void testStressDelete() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            Random r = new Random();
            HashSet<Integer> list = new HashSet<Integer>();
            for (int i = 0; i < 10000000; ++i) {
                int key = r.nextInt(10000);
                list.add(key);
                redBlackTree.insert(key, "soso" + key);
            }
            for (Integer elem : list) {
                redBlackTree.delete(elem);
            }
            INode<Integer, String> node = redBlackTree.getRoot();
            if (node != null && !node.isNull()) {
                Assert.fail();
            }
            Assert.assertTrue((boolean) this.verifyProps(node));
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle deletion", e);
        }
    }

    @Test
    public void testDeleteAbsentElementsInTree() {
        IRedBlackTree redBlackTree = (IRedBlackTree) TestRunner
                .getImplementationInstanceForInterface(IRedBlackTree.class);
        try {
            int key;
            int i;
            Random r = new Random();
            HashSet<Integer> list = new HashSet<Integer>();
            for (i = 0; i < 100000; ++i) {
                key = r.nextInt(10000);
                redBlackTree.insert(key, "soso" + key);
                list.add(key);
            }
            for (i = 0; i < 100; ++i) {
                key = r.nextInt(10000);
                if (list.contains(key))
                    continue;
                Assert.assertFalse((boolean) redBlackTree.delete(key));
            }
            Assert.assertTrue((boolean) this.verifyProps(redBlackTree.getRoot()));
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle deletion", e);
        }
    }

    @Test
    public void testCeilingEntryWithNull() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            treemap.ceilingEntry(null);
            Assert.fail();
        } catch (RuntimeErrorException runtimeErrorException) {
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle ceiling with null parameter", e);
        }
    }

    @Test
    public void testCeilingEntry1() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(1000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            Collections.shuffle(list);
            int key = (Integer) list.get(r.nextInt(list.size()));
            Map.Entry entry = treemap.ceilingEntry(key);
            Assert.assertEquals((long) key, (long) ((Integer) entry.getKey()).intValue());
            Assert.assertEquals((Object) ("soso" + key), entry.getValue());
        } catch (Throwable e) {
            TestRunner.fail("Fail in ceiling entry", e);
        }
    }

    @Test
    public void testCeilingEntry2() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random r = new Random();
            for (int i = 0; i < 10000; ++i) {
                int key = r.nextInt(1000000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            Collections.sort(list);
            int key = (Integer) list.get(list.size() / 2) - 2;
            for (int i = 0; i < list.size(); ++i) {
                if (key > (Integer) list.get(i))
                    continue;
                key = (Integer) list.get(i);
                break;
            }
            Map.Entry entry = treemap.ceilingEntry(key);
            Assert.assertEquals((long) key, (long) ((Integer) entry.getKey()).intValue());
            Assert.assertEquals((Object) ("soso" + key), entry.getValue());
        } catch (Throwable e) {
            TestRunner.fail("Fail in ceiling entry", e);
        }
    }

    @Test
    public void testCeilingKeyWithNull() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            treemap.ceilingKey(null);
            Assert.fail();
        } catch (RuntimeErrorException runtimeErrorException) {
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle ceiling with null parameter", e);
        }
    }

    @Test
    public void testCeilingKey1() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(1000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            Collections.shuffle(list);
            int key = (Integer) list.get(r.nextInt(list.size()));
            Integer entry = (Integer) treemap.ceilingKey(key);
            Assert.assertEquals((long) key, (long) entry.intValue());
        } catch (Throwable e) {
            TestRunner.fail("Fail in ceiling entry", e);
        }
    }

    @Test
    public void testCeilingKey2() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random r = new Random();
            for (int i = 0; i < 10000; ++i) {
                int key = r.nextInt(1000000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            Collections.sort(list);
            int key = (Integer) list.get(list.size() / 2) - 2;
            for (int i = 0; i < list.size(); ++i) {
                if (key > (Integer) list.get(i))
                    continue;
                key = (Integer) list.get(i);
                break;
            }
            Integer entry = (Integer) treemap.ceilingKey(key);
            Assert.assertEquals((long) key, (long) entry.intValue());
        } catch (Throwable e) {
            TestRunner.fail("Fail in ceiling key", e);
        }
    }

    @Test
    public void testClearElementsInTreeMap() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random r = new Random();
            for (int i = 0; i < 10000; ++i) {
                int key = r.nextInt(1000000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            treemap.clear();
            Assert.assertEquals((long) 0L, (long) treemap.size());
        } catch (Throwable e) {
            TestRunner.fail("Fail in clearing elments from treemap", e);
        }
    }

    @Test
    public void testContaninKeyWithNullparameter() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            treemap.containsKey(null);
            Assert.fail();
        } catch (RuntimeErrorException runtimeErrorException) {
        } catch (Throwable e) {
            TestRunner.fail("Fail in handle containsKey with null parameter", e);
        }
    }

    @Test
    public void testContaninKeyNormal() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(10000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            Collections.shuffle(list);
            int key = (Integer) list.get(r.nextInt(list.size()));
            Assert.assertTrue((boolean) treemap.containsKey(key));
        } catch (Throwable e) {
            TestRunner.fail("Fail in containsKey", e);
        }
    }

    @Test
    public void testContaninKeyNotFound() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(10000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            Assert.assertFalse((boolean) treemap.containsKey(100001));
        } catch (Throwable e) {
            TestRunner.fail("Fail in containsKey", e);
        }
    }

    @Test
    public void testContanisValueWithNullparameter() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            treemap.containsValue(null);
            Assert.fail();
        } catch (RuntimeErrorException runtimeErrorException) {
        } catch (Throwable e) {
            TestRunner.fail("Fail in containsValue with null parameter", e);
        }
    }

    @Test
    public void testContanisValueNormal() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(1000000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            Collections.shuffle(list);
            String key = "soso" + list.get(r.nextInt(list.size()));
            Assert.assertTrue((boolean) treemap.containsValue(key));
        } catch (Throwable e) {
            TestRunner.fail("Fail in containsValue", e);
        }
    }

    @Test
    public void testContanisValueNotFound() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(10000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            Assert.assertFalse((boolean) treemap.containsValue("koko100001"));
        } catch (Throwable e) {
            TestRunner.fail("Fail in containsValue", e);
        }
    }

    @Test
    public void testEntrySet() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            TreeMap<Integer, String> t = new TreeMap<Integer, String>();
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(10000);
                t.put(key, "soso" + key);
                treemap.put(key, "soso" + key);
            }
            Iterator itr1 = treemap.entrySet().iterator();
            Iterator itr2 = t.entrySet().iterator();
            while (itr1.hasNext() && itr2.hasNext()) {
                Map.Entry entry1 = (Entry) itr1.next();
                Map.Entry entry2 = (Entry) itr2.next();
                Assert.assertEquals(entry1, entry2);
            }
        } catch (Throwable e) {
            TestRunner.fail("Fail in entrySet", e);
        }
    }

    @Test
    public void testFirstEntry() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            TreeMap<Integer, String> t = new TreeMap<Integer, String>();
            Assert.assertEquals(t.firstEntry(), treemap.firstEntry());
            t.put(5, "soso5");
            treemap.put(5, "soso5");
            Assert.assertEquals(t.firstEntry(), treemap.firstEntry());
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                t.put(key, "soso" + key);
                treemap.put(key, "soso" + key);
            }
            Assert.assertEquals(t.firstEntry(), treemap.firstEntry());
        } catch (Throwable e) {
            TestRunner.fail("Fail in firstEntry", e);
        }
    }

    @Test
    public void testFirstKey() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            TreeMap<Integer, String> t = new TreeMap<Integer, String>();
            Assert.assertNull(treemap.firstKey());
            t.put(5, "soso5");
            treemap.put(5, "soso5");
            Assert.assertEquals(t.firstKey(), treemap.firstKey());
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                t.put(key, "soso" + key);
                treemap.put(key, "soso" + key);
            }
            Assert.assertEquals(t.firstKey(), treemap.firstKey());
        } catch (Throwable e) {
            TestRunner.fail("Fail in firstKey", e);
        }
    }

    @Test
    public void testfloorEntryWithNull() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            treemap.floorEntry(null);
            Assert.fail();
        } catch (RuntimeErrorException runtimeErrorException) {
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle floorEntry with null parameter", e);
        }
    }

    @Test
    public void testfloorEntry1() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(1000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            Collections.shuffle(list);
            int key = (Integer) list.get(r.nextInt(list.size()));
            Map.Entry entry = treemap.floorEntry(key);
            Assert.assertEquals((long) key, (long) ((Integer) entry.getKey()).intValue());
            Assert.assertEquals((Object) ("soso" + key), entry.getValue());
        } catch (Throwable e) {
            TestRunner.fail("Fail in floorEntry", e);
        }
    }

    @Test
    public void testfloorEntry2() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random r = new Random();
            for (int i = 0; i < 10000; ++i) {
                int key = r.nextInt(1000000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            Collections.sort(list);
            int key = (Integer) list.get(list.size() / 2) - 2;
            for (int i = 0; i < list.size(); ++i) {
                if ((Integer) list.get(i) > key)
                    break;
                key = (Integer) list.get(i);
            }
            Map.Entry entry = treemap.floorEntry(key);
            Assert.assertEquals((long) key, (long) ((Integer) entry.getKey()).intValue());
            Assert.assertEquals((Object) ("soso" + key), entry.getValue());
        } catch (Throwable e) {
            TestRunner.fail("Fail in floor entry", e);
        }
    }

    @Test
    public void testfloorKeyWithNull() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            treemap.floorKey(null);
            Assert.fail();
        } catch (RuntimeErrorException runtimeErrorException) {
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle floorKey with null parameter", e);
        }
    }

    @Test
    public void testfloorKey1() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(1000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            Collections.shuffle(list);
            int key = (Integer) list.get(r.nextInt(list.size()));
            Integer entry = (Integer) treemap.floorKey(key);
            Assert.assertEquals((long) key, (long) entry.intValue());
        } catch (Throwable e) {
            TestRunner.fail("Fail in floorKey", e);
        }
    }

    @Test
    public void testfloorKey2() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random r = new Random();
            for (int i = 0; i < 10000; ++i) {
                int key = r.nextInt(1000000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            Collections.sort(list);
            int key = (Integer) list.get(list.size() / 2) - 2;
            for (int i = 0; i < list.size(); ++i) {
                if ((Integer) list.get(i) > key)
                    break;
                key = (Integer) list.get(i);
            }
            Integer entry = (Integer) treemap.floorKey(key);
            Assert.assertEquals((long) key, (long) entry.intValue());
        } catch (Throwable e) {
            TestRunner.fail("Fail in floor key", e);
        }
    }

    @Test
    public void testGetElementInTreemap() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            TreeMap<Integer, String> t = new TreeMap<Integer, String>();
            Random r = new Random();
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < 10000; ++i) {
                int key = r.nextInt(1000000);
                String val = "" + r.nextInt(10000);
                list.add(key);
                t.put(key, val);
                treemap.put(key, val);
            }
            Collections.shuffle(list);
            int index = (Integer) list.get(r.nextInt(list.size()));
            Assert.assertEquals(t.get(index), treemap.get(index));
        } catch (Throwable e) {
            TestRunner.fail("Fail in get element from treemap", e);
        }
    }

    @Test
    public void testGetElementInTreemapWithNullParamerter() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            treemap.get(null);
            Assert.fail();
        } catch (RuntimeErrorException runtimeErrorException) {
        } catch (Throwable e) {
            TestRunner.fail("Fail to handle get with null parameter", e);
        }
    }

    private String levelOrder(INode<Integer, String> root) {
        StringBuilder sb = new StringBuilder();
        LinkedList q = new LinkedList();
        q.add(root);
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; ++i) {
                INode cur = (INode) q.poll();
                if (cur != null && !cur.isNull()) {
                    sb.append(cur.getKey() + (cur.getColor() ? "R" : "B"));
                    q.add(cur.getLeftChild());
                    q.add(cur.getRightChild());
                    continue;
                }
                sb.append("NB");
            }
        }
        return sb.toString();
    }

    @Test
    public void testGetElementInTreemapNotFound() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            Random r = new Random();
            for (int i = 0; i < 10000; ++i) {
                int key = r.nextInt(1000000);
                String val = "" + r.nextInt(10000);
                treemap.put(key, val);
            }
            Assert.assertNull(treemap.get(1000001));
        } catch (Throwable e) {
            TestRunner.fail("Fail in get element from treemap", e);
        }
    }

    @Test
    public void testHeadMapWithNullparameter() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            treemap.headMap(null);
        } catch (RuntimeErrorException runtimeErrorException) {
        } catch (Throwable e) {
            TestRunner.fail("Fail in handle headMap with null parameter", e);
        }
    }

    @Test
    public void testHeadMap() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            TreeMap<Integer, String> t = new TreeMap<Integer, String>();
            Random r = new Random();
            ArrayList<Integer> keys = new ArrayList<Integer>();
            for (int i = 0; i < 10000; ++i) {
                int key = r.nextInt(1000000);
                String val = "" + r.nextInt(10000);
                treemap.put(key, val);
                t.put(key, val);
                keys.add(key);
            }
            int toKey = (Integer) keys.get(r.nextInt(keys.size()));
            ArrayList ans = treemap.headMap(toKey);
            ArrayList realAns = new ArrayList(t.headMap(toKey).entrySet());
            Collections.sort(realAns, new Comparator<Map.Entry<Integer, String>>() {

                @Override
                public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                    return o1.getKey() - o2.getKey();
                }
            });
            for (int i = 0; i < ans.size(); ++i) {
                Assert.assertEquals(ans.get(i), realAns.get(i));
            }
        } catch (Throwable e) {
            TestRunner.fail("Fail in headMap", e);
        }
    }

    @Test
    public void testHeadMapInclusiveWithNullparameter() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            treemap.headMap(null, true);
        } catch (RuntimeErrorException runtimeErrorException) {
        } catch (Throwable e) {
            TestRunner.fail("Fail in handle headMap with null parameter", e);
        }
    }

    @Test
    public void testHeadMapInclusive() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            TreeMap<Integer, String> t = new TreeMap<Integer, String>();
            Random r = new Random();
            ArrayList<Integer> keys = new ArrayList<Integer>();
            for (int i = 0; i < 10000; ++i) {
                int key = r.nextInt(1000000);
                String val = "" + r.nextInt(10000);
                treemap.put(key, val);
                t.put(key, val);
                keys.add(key);
            }
            int toKey = (Integer) keys.get(r.nextInt(keys.size()));
            ArrayList ans = treemap.headMap(toKey, true);
            ArrayList realAns = new ArrayList(t.headMap(toKey, true).entrySet());
            Collections.sort(realAns, new Comparator<Map.Entry<Integer, String>>() {

                @Override
                public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                    return o1.getKey() - o2.getKey();
                }
            });
            for (int i = 0; i < ans.size(); ++i) {
                Assert.assertEquals(ans.get(i), realAns.get(i));
            }
        } catch (Throwable e) {
            TestRunner.fail("Fail in headMap", e);
        }
    }

    @Test
    public void testKeySet() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            TreeMap<Integer, String> t = new TreeMap<Integer, String>();
            Random r = new Random();
            ArrayList<Integer> keys = new ArrayList<Integer>();
            for (int i = 0; i < 10000; ++i) {
                int key = r.nextInt(1000000);
                String val = "" + r.nextInt(10000);
                treemap.put(key, val);
                t.put(key, val);
                keys.add(key);
            }
            Set<Integer> ans = treemap.keySet();
            ArrayList<Integer> realAns = new ArrayList(t.keySet());
            Collections.sort(realAns);
            int i = 0;
            for (Integer elem : ans) {
                Assert.assertEquals((Object) elem, realAns.get(i++));
            }
        } catch (Throwable e) {
            TestRunner.fail("Fail in Keyset", e);
        }
    }

    @Test
    public void testLastEntry() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            TreeMap<Integer, String> t = new TreeMap<Integer, String>();
            Assert.assertEquals(t.lastEntry(), treemap.lastEntry());
            t.put(5, "soso5");
            treemap.put(5, "soso5");
            Assert.assertEquals(t.lastEntry(), treemap.lastEntry());
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                t.put(key, "soso" + key);
                treemap.put(key, "soso" + key);
            }
            Assert.assertEquals(t.lastEntry(), treemap.lastEntry());
        } catch (Throwable e) {
            TestRunner.fail("Fail in lastEntry", e);
        }
    }

    @Test
    public void testLastKey() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            TreeMap<Integer, String> t = new TreeMap<Integer, String>();
            Assert.assertNull(treemap.lastKey());
            t.put(5, "soso5");
            treemap.put(5, "soso5");
            Assert.assertEquals(t.lastKey(), treemap.lastKey());
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                t.put(key, "soso" + key);
                treemap.put(key, "soso" + key);
            }
            Assert.assertEquals(t.lastKey(), treemap.lastKey());
        } catch (Throwable e) {
            TestRunner.fail("Fail in lastKey", e);
        }
    }

    @Test
    public void testpollFirstEntry() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            TreeMap<Integer, String> t = new TreeMap<Integer, String>();
            Assert.assertEquals((long) t.size(), (long) treemap.size());
            Assert.assertNull(treemap.pollFirstEntry());
            t.put(5, "soso5");
            treemap.put(5, "soso5");
            Assert.assertEquals(t.pollFirstEntry(), treemap.pollFirstEntry());
            Assert.assertEquals((long) t.size(), (long) treemap.size());
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                t.put(key, "soso" + key);
                treemap.put(key, "soso" + key);
            }
            Assert.assertEquals((long) t.size(), (long) treemap.size());
            Assert.assertEquals(t.pollFirstEntry(), treemap.pollFirstEntry());
            Assert.assertEquals((long) t.size(), (long) treemap.size());
        } catch (Throwable e) {
            TestRunner.fail("Fail in pollFirstEntry", e);
        }
    }

    @Test
    public void testpollLastEntry() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            TreeMap<Integer, String> t = new TreeMap<Integer, String>();
            Assert.assertEquals((long) t.size(), (long) treemap.size());
            Assert.assertNull(treemap.pollLastEntry());
            t.put(5, "soso5");
            treemap.put(5, "soso5");
            Assert.assertEquals(t.pollLastEntry(), treemap.pollLastEntry());
            Assert.assertEquals((long) t.size(), (long) treemap.size());
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                t.put(key, "soso" + key);
                treemap.put(key, "soso" + key);
            }
            Assert.assertEquals((long) t.size(), (long) treemap.size());
            Assert.assertEquals(t.pollLastEntry(), treemap.pollLastEntry());
            Assert.assertEquals((long) t.size(), (long) treemap.size());
        } catch (Throwable e) {
            TestRunner.fail("Fail in pollLastEntry", e);
        }
    }

    @Test
    public void testputWithNullKey() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            treemap.put(null, "soso");
            Assert.fail();
        } catch (RuntimeErrorException runtimeErrorException) {
        } catch (Throwable e) {
            TestRunner.fail("Fail in handle put with null parameter", e);
        }
    }

    @Test
    public void testputWithNullValue() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            treemap.put(123, null);
            Assert.fail();
        } catch (RuntimeErrorException runtimeErrorException) {
        } catch (Throwable e) {
            TestRunner.fail("Fail in handle put with null parameter", e);
        }
    }

    @Test
    public void testputAllWithNullValue() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            treemap.putAll(null);
            Assert.fail();
        } catch (RuntimeErrorException runtimeErrorException) {
        } catch (Throwable e) {
            TestRunner.fail("Fail in handle putAll with null parameter", e);
        }
    }

    @Test
    public void testputAll() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            Random r = new Random();
            HashMap<Integer, String> map = new HashMap<Integer, String>();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(1000);
                map.put(key, "soso" + key);
            }
            treemap.putAll(map);
            Assert.assertEquals((long) map.size(), (long) treemap.size());
        } catch (Throwable e) {
            TestRunner.fail("Fail in putAll", e);
        }
    }

    @Test
    public void testRemoveWithNullparameter() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            treemap.remove(null);
        } catch (RuntimeErrorException runtimeErrorException) {
        } catch (Throwable e) {
            TestRunner.fail("Fail in remove with null parameter", e);
        }
    }

    @Test
    public void testRemoveNoraml() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            Random r = new Random();
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            Collections.shuffle(list);
            int keyToRem = (Integer) list.get(r.nextInt(list.size()));
            Assert.assertTrue((boolean) treemap.remove(keyToRem));
        } catch (Throwable e) {
            TestRunner.fail("Fail in remove", e);
        }
    }

    @Test
    public void testRemoveNotFound() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            Random r = new Random();
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                list.add(key);
                treemap.put(key, "soso" + key);
            }
            Assert.assertFalse((boolean) treemap.remove(100001));
        } catch (Throwable e) {
            TestRunner.fail("Fail in remove element", e);
        }
    }

    @Test
    public void testValues() {
        ITreeMap treemap = (ITreeMap) TestRunner.getImplementationInstanceForInterface(ITreeMap.class);
        try {
            TreeMap<Integer, String> t = new TreeMap<Integer, String>();
            Random r = new Random();
            for (int i = 0; i < 1000; ++i) {
                int key = r.nextInt(100000);
                treemap.put(key, "soso" + key);
                t.put(key, "soso" + key);
            }
            Collection ans = treemap.values();
            Collection ansReal = t.values();
            Object[] ansArr = ans.toArray();
            Object[] ansRealArr = ansReal.toArray();
            Arrays.sort(ansArr);
            Arrays.sort(ansRealArr);
            for (int i = 0; i < Math.max(ansArr.length, ansRealArr.length); ++i) {
                Assert.assertEquals((Object) ansArr[i], (Object) ansRealArr[i]);
            }
        } catch (Throwable e) {
            TestRunner.fail("Fail in getting values", e);
        }
    }

    private boolean validateBST(INode<Integer, String> node, INode<Integer, String> leftRange,
                                INode<Integer, String> rightRange) {
        if (node == null || node.isNull()) {
            return true;
        }
        if (!(leftRange != null && node.getKey().compareTo(leftRange.getKey()) <= 0
                || rightRange != null && node.getKey().compareTo(rightRange.getKey()) >= 0)) {
            return this.validateBST(node.getLeftChild(), leftRange, node)
                    && this.validateBST(node.getRightChild(), node, rightRange);
        }
        return false;
    }

    private boolean verifyProperty2(INode<Integer, String> node) {
        return !node.getColor();
    }

    private boolean verifyProperty3(INode<Integer, String> node) {
        if (node == null || node.isNull()) {
            return !node.getColor();
        }
        return this.verifyProperty3(node.getLeftChild()) && this.verifyProperty3(node.getRightChild());
    }

    private boolean verifyProperty4(INode<Integer, String> node) {
        if (node == null || node.isNull()) {
            return true;
        }
        if (this.isRed(node)) {
            return !this.isRed(node.getParent()) && !this.isRed(node.getLeftChild())
                    && !this.isRed(node.getRightChild());
        }
        return this.verifyProperty4(node.getLeftChild()) && this.verifyProperty4(node.getRightChild());
    }

    private boolean verifyProperty5(INode<Integer, String> node) {
        boolean[] ans = new boolean[]{true};
        this.verifyProperty5Helper(node, ans);
        return ans[0];
    }

    private int verifyProperty5Helper(INode<Integer, String> node, boolean[] ans) {
        if (node == null) {
            return 1;
        }
        int leftCount = this.verifyProperty5Helper(node.getLeftChild(), ans);
        int rightCount = this.verifyProperty5Helper(node.getRightChild(), ans);
        ans[0] = ans[0] && leftCount == rightCount;
        return leftCount + (!this.isRed(node) ? 1 : 0);
    }

//    private void printit(INode x){
//        if(x==null || x.isNull())
//            return;
//        printit(x.getLeftChild());
//        System.out.print(x.getKey() + " ");
//        printit(x.getRightChild());
//    }

    private boolean verifyProps(INode<Integer, String> root) {
//        System.out.println("prop2: " + this.verifyProperty2(root));
//        System.out.println("prop3: " + this.verifyProperty3(root));
//        System.out.println("prop4: " + this.verifyProperty4(root));
//        System.out.println("prop5: " + this.verifyProperty5(root));
//        System.out.println("propbt: " + this.validateBST(root, null, null));
//        printit(root);
        return this.verifyProperty2(root) && this.verifyProperty3(root) && this.verifyProperty4(root)
                && this.verifyProperty5(root) && this.validateBST(root, null, null);
    }

    private boolean isRed(INode<Integer, String> node) {
        if (node == null || node.isNull()) {
            return false;
        }
        return node.getColor();
    }

}
