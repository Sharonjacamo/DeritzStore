
package com.deritzstore.app

import com.deritzstore.app.data.memory.MemoryDataRepository
import com.deritzstore.app.viewmodel.ClientViewModel

object StoreData {

    val repository = MemoryDataRepository()

    val clientViewModel = ClientViewModel(repository)

}
