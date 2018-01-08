/**
 * @author Shivashriganesh Mahato
 */
public class ZCriticalNumber {
    public static void main(String[] args) {
        ZCriticalNumber number = new ZCriticalNumber();
        System.out.println(number.getCriticalNumber(95));
    }

    private double getCriticalNumber(int confidence) {
        float alpha = (confidence + ((100 - confidence) / 2.0f)) / 100.0f;
        return invNorm(alpha);
    }

    private double invNorm(float p) {
        float param = 2 * p - 1;
        return Math.sqrt(2) * invErf(param, 20);
    }

    /**
     * inv. erf(z) = ∑{k=0 → ∞} (c_k/(2k+1))((sqrt(π)/2)(z))^(2k+1)
     */
    private double invErf(float param, int n) {
        double sum = 0;

        for (int k = 0; k <= n; k++) {
            double constTerm = cthTerm(k) / (2 * k + 1);
            double base = ((Math.sqrt(Math.PI) / 2) * param);
            double powerTerm = Math.pow(base, (2 * k + 1));
            sum += constTerm * powerTerm;
        }

        return sum;
    }

    /**
     * c_0 = 1,
     * c_k = ∑{m=0 → k-1} (c_m * c_(k-1-m))/((m+1)(2m+1))
     */
    private double cthTerm(int k) {
        if (k == 0)
            return 1;

        double sum = 0;

        for (int m = 0; m <= k - 1; m++) {
            double num = cthTerm(m) * cthTerm(k - 1 - m);
            double denom = (m + 1) * (2 * m + 1);
            sum += num / denom;
        }

        return sum;
    }
}
