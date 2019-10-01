package fit.tdc.edu.vn.cafemanagement.data.model;

public class Zone {
    private String id;
    private String name;
    private String zoneTypeID;

    Zone(String id, String name, String zoneTypeID) {
        this.id = id;
        this.name = name;
        this.zoneTypeID = zoneTypeID;
    }

    public static ZoneBuilder builder() {
        return new ZoneBuilder();
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getZoneTypeID() {
        return this.zoneTypeID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setZoneTypeID(String zoneTypeID) {
        this.zoneTypeID = zoneTypeID;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Zone)) return false;
        final Zone other = (Zone) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$zoneTypeID = this.getZoneTypeID();
        final Object other$zoneTypeID = other.getZoneTypeID();
        if (this$zoneTypeID == null ? other$zoneTypeID != null : !this$zoneTypeID.equals(other$zoneTypeID))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Zone;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $zoneTypeID = this.getZoneTypeID();
        result = result * PRIME + ($zoneTypeID == null ? 43 : $zoneTypeID.hashCode());
        return result;
    }

    public String toString() {
        return "Zone(id=" + this.getId() + ", name=" + this.getName() + ", zoneTypeID=" + this.getZoneTypeID() + ")";
    }

    public static class ZoneBuilder {
        private String id;
        private String name;
        private String zoneTypeID;

        ZoneBuilder() {
        }

        public ZoneBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ZoneBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ZoneBuilder zoneTypeID(String zoneTypeID) {
            this.zoneTypeID = zoneTypeID;
            return this;
        }

        public Zone build() {
            return new Zone(id, name, zoneTypeID);
        }

        public String toString() {
            return "Zone.ZoneBuilder(id=" + this.id + ", name=" + this.name + ", zoneTypeID=" + this.zoneTypeID + ")";
        }
    }
}
