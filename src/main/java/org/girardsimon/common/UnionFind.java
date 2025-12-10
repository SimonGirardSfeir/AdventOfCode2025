package org.girardsimon.common;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public record UnionFind(int[] parent, int [] rank, int[] size) {

    public UnionFind(int n) {
        int[] parent = new int[n];
        int[] rank = new int[n];
        int[] size = new int[n];
        IntStream.range(0, n).forEach(i -> {
            parent[i] = i;
            rank[i] = 1;
            size[i] = 1;
        });
        this(parent, rank, size);
    }

    private int find(int x) {
        if (x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) {
            return false;
        }
        if(rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
            return size[rootB] == parent.length;
        } else if(rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            return size[rootA] == parent.length;
        } else {
            parent[rootB] = rootA;
            rank[rootA]++;
            size[rootA] += size[rootB];
            return size[rootA] == parent.length;
        }
    }

    public List<Integer> groupSizes() {
        return IntStream.range(0, parent.length)
                .filter(i -> parent[i] == i)
                .map(i -> size[i])
                .boxed()
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UnionFind unionFind = (UnionFind) o;
        return Arrays.equals(size, unionFind.size) && Arrays.equals(rank, unionFind.rank) && Arrays.equals(parent, unionFind.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(parent), Arrays.hashCode(rank), Arrays.hashCode(size));
    }

    @Override
    public String toString() {
        return "UnionFind{" +
                "parent=" + Arrays.toString(parent) +
                ", rank=" + Arrays.toString(rank) +
                ", size=" + Arrays.toString(size) +
                '}';
    }
}
