import java.util.*;

class N {
    char c;
    int f;
    N l, r;
    N(char c, int f) { this.c = c; this.f = f; }
    N(int f, N l, N r) { this.c = '-'; this.f = f; this.l = l; this.r = r; }
}

public class Huffman {
    static void codes(N n, String s, Map<Character, String> m) {
        if (n == null) return;
        if (n.l == null && n.r == null) m.put(n.c, s);
        codes(n.l, s + "0", m);
        codes(n.r, s + "1", m);
    }

    static N build(Map<Character, Integer> f) {
        PriorityQueue<N> q = new PriorityQueue<>(Comparator.comparingInt(a -> a.f));
        for (var e : f.entrySet()) q.add(new N(e.getKey(), e.getValue()));
        while (q.size() > 1) {
            N a = q.poll(), b = q.poll();
            q.add(new N(a.f + b.f, a, b));
        }
        return q.peek();
    }

    static String enc(String t, Map<Character, String> m) {
        StringBuilder sb = new StringBuilder();
        for (char c : t.toCharArray()) sb.append(m.get(c));
        return sb.toString();
    }

    static String dec(String s, N r) {
        StringBuilder sb = new StringBuilder();
        N n = r;
        for (char c : s.toCharArray()) {
            n = (c == '0') ? n.l : n.r;
            if (n.l == null && n.r == null) {
                sb.append(n.c);
                n = r;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String t = sc.nextLine();
        sc.close();

        Map<Character, Integer> f = new HashMap<>();
        for (char c : t.toCharArray()) f.put(c, f.getOrDefault(c, 0) + 1);

        N r = build(f);
        Map<Character, String> m = new HashMap<>();
        codes(r, "", m);

        System.out.println("\nCodes: " + m);
        String e = enc(t, m);
        System.out.println("Encoded: " + e);
        System.out.println("Decoded: " + dec(e, r));
    }
}