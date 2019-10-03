package fit.tdc.edu.vn.cafemanagement.data.model;

import java.util.List;

public class MenuItems {
    private String menuID;
    private List<Material> materials;
    private long amount;

    MenuItems(String menuID, List<Material> materials, long amount) {
        this.menuID = menuID;
        this.materials = materials;
        this.amount = amount;
    }

    public static MenuItemsBuilder builder() {
        return new MenuItemsBuilder();
    }

    public static class MenuItemsBuilder {
        private String menuID;
        private List<Material> materials;
        private long amount;

        MenuItemsBuilder() {
        }

        public MenuItemsBuilder menuID(String menuID) {
            this.menuID = menuID;
            return this;
        }

        public MenuItemsBuilder materials(List<Material> materials) {
            this.materials = materials;
            return this;
        }

        public MenuItemsBuilder amount(long amount) {
            this.amount = amount;
            return this;
        }

        public MenuItems build() {
            return new MenuItems(menuID, materials, amount);
        }

        public String toString() {
            return "MenuItems.MenuItemsBuilder(menuID=" + this.menuID + ", materials=" + this.materials + ", amount=" + this.amount + ")";
        }
    }
}
