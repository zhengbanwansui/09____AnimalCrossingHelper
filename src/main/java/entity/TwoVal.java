package entity;

import java.util.Objects;

public class TwoVal {
    private int a;
    private int b;

    public TwoVal(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        TwoVal twoValObj = (TwoVal)obj;
        return a == twoValObj.a && b == twoValObj.b;
    }
}
