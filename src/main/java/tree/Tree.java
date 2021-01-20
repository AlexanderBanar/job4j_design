package tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> rsl = findBy(parent);
        if (rsl.isEmpty() || findBy(child).isPresent()) {
            return false;
        }
        return rsl.get().children.add(new Node<E>(child));
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> pred = x -> x.value.equals(value);
        return findByPred(pred);
    }

    public boolean isBinary() {
        Predicate<Node<E>> pred = x -> x.children.size() > 2;
        return findByPred(pred).isEmpty();
    }

    private Optional<Node<E>> findByPred(Predicate<Node<E>> pred) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (pred.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
