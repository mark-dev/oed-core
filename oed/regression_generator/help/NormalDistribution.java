package regression_generator.help;


//Нормальное распределение
// Передаем в конструктор сигму и мат. ожидание
// метод next возвратит велечину распределенную по нормальному закону.
public class NormalDistribution {

    private final double sigma;
    private final double mx;

    public NormalDistribution(double m, double sx) {
        mx = m;
        sigma = sx;
    }

    public double[] generateArray(int length) {
        double[] result = new double[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = next();
        }
        return result;
    }

    double next() {
        boolean solved = false;
        double x1 = 0;
        double normalValue = 0;
        while (!solved) {
            double R1 = Math.random();
            double R2 = Math.random();
            double zeroThree = 1.0 / 3;//0.3(3)
            double zeroSix = 2.0 / 3;//0.6(6)
            if (R1 <= zeroThree) {
                x1 = -0.5 + Math.log(3 * R1);
            }
            if (R1 > zeroThree && R1 <= zeroSix) {
                x1 = -1.5 + 3 * R1;
            }
            if (R1 > zeroSix) {
                x1 = 0.5 - Math.log(3 - 3 * R1);
            }
            if (R2 <= f1(x1) / g(x1)) {
                normalValue = Math.sqrt(sigma) * x1 + mx;
                solved = true;
            }
        }
        return normalValue;
    }

    private double g(double x) {
        double result;
        if (Math.abs(x) > 0.5) {
            result = Math.exp(-Math.abs(x) + 0.5);
        } else {
            result = 1;
        }
        return result / Math.sqrt(Math.PI * 2);
    }

    // Плотность распределения для  N(0;1)
    private double f1(double x) {
        return (Math.exp((-Math.pow(x, 2)) / 2) / Math.sqrt(2 * Math.PI));
    }

}
