package fit.tdc.edu.vn.cafemanagement.data.model;

public class Unit {
    private String id;
    private String name;

    Unit(UnitBuilder unitBuilder) {
        this.id = unitBuilder.id;
        this.name = unitBuilder.name;
    }

    public static UnitBuilder builder() {
        return new UnitBuilder();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Unit)) return false;
        final Unit other = (Unit) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Unit;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "Unit(id=" + this.getId() + ", name=" + this.getName() + ")";
    }

    public static class UnitBuilder {
        private String id;
        private String name;

        UnitBuilder() {
        }

        public UnitBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UnitBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Unit build() {
            return new Unit(this);
        }

        public String toString() {
            return "Unit.UnitBuilder(id=" + this.id + ", name=" + this.name + ")";
        }
    }
}
