package com.epi;

import com.epi.BinarySearchTreePrototypeTemplate.BSTNode;

import java.util.ArrayList;
import java.util.List;

public class RebuildBSTPreorder {
  // @include
  public static BSTNode<Integer> rebuildBSTFromPreorder(
      List<Integer> preorderSequence) {
    return rebuildBSTFromPreorderHelper(preorderSequence, 0,
                                        preorderSequence.size());
  }

  // Builds a BST from preorderSequence[start : end - 1].
  private static BSTNode<Integer> rebuildBSTFromPreorderHelper(
      List<Integer> preorderSequence, int start, int end) {
    if (start >= end) {
      return null;
    }
    int transitionPoint = start + 1;
    while (transitionPoint < end &&
           preorderSequence.get(transitionPoint).compareTo(preorderSequence.get(start)) < 0) {
      ++transitionPoint;
    }
    return new BSTNode<>(
        preorderSequence.get(start),
        rebuildBSTFromPreorderHelper(preorderSequence, start + 1, transitionPoint),
        rebuildBSTFromPreorderHelper(preorderSequence, transitionPoint, end));
  }
  // @exclude

  private static void checkAns(BSTNode<Integer> n, Integer pre) {
    if (n != null) {
      checkAns(n.getLeft(), pre);
      assert (pre.compareTo(n.getData()) <= 0);
      System.out.println(n.getData());
      checkAns(n.getRight(), n.getData());
    }
  }

  public static void main(String[] args) {
    // 3
    // 2 5
    // 1 4 6
    // should output 1, 2, 3, 4, 5, 6
    // preorder [3, 2, 1, 5, 4, 6]
    List<Integer> preorder = new ArrayList<>();
    preorder.add(3);
    preorder.add(2);
    preorder.add(1);
    preorder.add(5);
    preorder.add(4);
    preorder.add(6);
    BSTNode<Integer> tree = rebuildBSTFromPreorder(preorder);
    checkAns(tree, Integer.MIN_VALUE);
  }
}
