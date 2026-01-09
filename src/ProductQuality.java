public enum ProductQuality {
    NORMAL(1.2) {
        @Override
        public ProductQuality downgrade() {
            return SLIGHTLY_SPOILED;
        }
    },
    SLIGHTLY_SPOILED(0.95) {
        @Override
        public ProductQuality downgrade() {
            return HALF_SPOILED;
        }
    },
    HALF_SPOILED(0.55) {
        @Override
        public ProductQuality downgrade() {
            return ALMOST_GONE;
        }
    },
    ALMOST_GONE(0.25) {
        @Override
        public ProductQuality downgrade() {
            return COMPLETELY_SPOILED;
        }
    },
    COMPLETELY_SPOILED(0.1) {
        @Override
        public ProductQuality downgrade() {
            return COMPLETELY_SPOILED;
        }
    };

    private final double coefficient;

    ProductQuality(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public abstract ProductQuality downgrade();
}
