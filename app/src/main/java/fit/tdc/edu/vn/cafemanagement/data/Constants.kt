package fit.tdc.edu.vn.cafemanagement.data

sealed class Constants {
    companion object {
        const val STORES_KEY        = "stores"
        const val CATEGORIES_KEY    = "categories"
        const val MATERIALS_KEY     = "materials"
        const val TABLES_KEY        = "tables"
        const val ORDERS_KEY        = "orders"
        const val REVENUES_KEY      = "revenues"
        const val UNITS_KEY         = "units"
        const val ZONE_TYPES_KEY    = "zoneTypes"
        const val ZONES_KEY         = "zones"
        const val USERS_KEY         = "employees"
        const val PAYMENTS_KEY      = "payments"
        const val WAREHOUSES_KEY      = "warehouses"

        const val STORE_ID_KEY      = "storeId"
        const val STORE_NAME_KEY    = "storeName"
        const val MANAGER_ID_KEY    = "managerId"
        const val MANAGER_NAME_KEY  = "managerName"
        const val CATEGORY_ID_KEY   = "categoryId"
        const val CATEGORY_NAME_KEY = "categoryName"
        const val UNIT_ID_KEY       = "unitId"
        const val UNIT_NAME_KEY     = "unitName"
        const val ZONE_ID_KEY       = "unitId"
        const val ZONE_NAME_KEY     = "unitName"
        const val TABLE_ID_KEY      = "tableId"
        const val TABLE_STATE_KEY   = "state"
        const val ORDER_ID_KEY      = "orderId"
        const val ORDER_STATE_KEY   = "state"
        const val PAYMENT_STATE_KEY = "state"
        const val PAYMENT_ID_KEY    = "paymentId"
    }
}