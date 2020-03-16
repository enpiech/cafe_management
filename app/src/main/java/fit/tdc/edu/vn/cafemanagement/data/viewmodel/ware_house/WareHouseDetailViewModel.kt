package fit.tdc.edu.vn.cafemanagement.data.viewmodel.ware_house

import androidx.lifecycle.SavedStateHandle
import com.google.firebase.Timestamp
import fit.tdc.edu.vn.cafemanagement.R
import fit.tdc.edu.vn.cafemanagement.data.model.ware_house.WareHouse
import fit.tdc.edu.vn.cafemanagement.data.model.ware_house.WareHouseViewFormState
import fit.tdc.edu.vn.cafemanagement.data.repository.ware_house.WareHouseRepository
import fit.tdc.edu.vn.cafemanagement.fragment.BaseDetailViewModel
import fit.tdc.edu.vn.cafemanagement.util.isValidPersonalName

class WareHouseDetailViewModel (
    private val handle: SavedStateHandle,
    private val wareHouseRepository: WareHouseRepository
) : BaseDetailViewModel<WareHouse>() {
    override var saved: WareHouse
        get() = WareHouse(
            name = handle.get("name"),
            stock = handle.get<String>("stock"),
            amount = handle.get("amount"),
            state = handle.get<WareHouse.State>("state") ?: WareHouse.State.INPUT,
            inDate = handle.get<Timestamp>("inDate"),
            outDate = handle.get<Timestamp>("outDate"),
            unitId = handle.get<String>("unitId"),
            unitName = handle.get<String>("unitName")

        )
        set(value) {
            handle.set("name", value.name)
            handle.set("stock", value.stock)
            handle.set("amount", value.amount)
            handle.set("state", value.state)
            handle.set("inDate", value.inDate)
            handle.set("outDate", value.outDate)
            handle.set("unitId", value.unitId)
            handle.set("unitName", value.unitName)
        }

    override fun getItem(id: String) = wareHouseRepository.getWareHouse(id)

    override fun insert(item: WareHouse) = wareHouseRepository.insert(item)

    override fun update(item: WareHouse) = wareHouseRepository.update(item)

    override fun validate(item: WareHouse?) {
        when (item) {
            null -> {
                _formState.value = WareHouseViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = true
                }
                return
            }
            currentItem.value -> {
                _formState.value = WareHouseViewFormState(
                    nameError = null
                ).apply {
                    isChanged = false
                    isDataValid = false
                }
            }
        }

        // SavedStateHandle
        saved = item!!

        if (_formState.value == null) {
            _formState.value = WareHouseViewFormState()
        }

        val formState = _formState.value as WareHouseViewFormState
        var noError = true

        if (item.name != null && !item.name.isValidPersonalName()) {
            formState.nameError = R.string.invalid_warehouse_name
            noError = false
        } else {
            formState.nameError = null
        }

        if (item.stock != null && !item.stock.isValidPersonalName()) {
            formState.stockError = R.string.invalid_warehouse_stock
            noError = false
        } else {
            formState.stockError = null
        }

        if (currentItem.value != null) {
            when {
                item.name != currentItem.value!!.name -> formState.isChanged = true
                item.stock != currentItem.value!!.stock -> formState.isChanged = true
                item.amount != currentItem.value!!.amount -> formState.isChanged = true
                item.state != currentItem.value!!.state -> formState.isChanged = true
                item.inDate != currentItem.value!!.inDate -> formState.isChanged = true
                item.outDate != currentItem.value!!.outDate -> formState.isChanged = true
                item.unitId != currentItem.value!!.unitId && item.unitName != currentItem.value!!.unitName -> formState.isChanged = true
            }
        } else {
            formState.isChanged = true
        }

        formState.isDataValid = noError
        _formState.value = formState
    }
}