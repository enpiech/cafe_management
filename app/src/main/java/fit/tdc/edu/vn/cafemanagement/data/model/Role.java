package fit.tdc.edu.vn.cafemanagement.data.model;

public class Role {
    private String id;
    private String roleName;

    Role(String id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public static RoleBuilder builder() {
        return new RoleBuilder();
    }

    public String getId() {
        return this.id;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Role)) return false;
        final Role other = (Role) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$roleName = this.getRoleName();
        final Object other$roleName = other.getRoleName();
        if (this$roleName == null ? other$roleName != null : !this$roleName.equals(other$roleName))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Role;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $roleName = this.getRoleName();
        result = result * PRIME + ($roleName == null ? 43 : $roleName.hashCode());
        return result;
    }

    public String toString() {
        return "Role(id=" + this.getId() + ", roleName=" + this.getRoleName() + ")";
    }

    public static class RoleBuilder {
        private String id;
        private String roleName;

        RoleBuilder() {
        }

        public RoleBuilder id(String id) {
            this.id = id;
            return this;
        }

        public RoleBuilder roleName(String roleName) {
            this.roleName = roleName;
            return this;
        }

        public Role build() {
            return new Role(id, roleName);
        }

        public String toString() {
            return "Role.RoleBuilder(id=" + this.id + ", roleName=" + this.roleName + ")";
        }
    }
}