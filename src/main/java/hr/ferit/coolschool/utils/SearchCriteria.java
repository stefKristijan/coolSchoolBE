package hr.ferit.coolschool.utils;

public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;

    public SearchCriteria(String key, String operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchCriteria that = (SearchCriteria) o;

        if (!key.equals(that.key)) return false;
        if (!operation.equals(that.operation)) return false;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + operation.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    public SearchCriteria(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}