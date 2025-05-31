package vm

import tools.ID

data class Environment(
    val variables : MutableMap<ID,SugarObject> = mutableMapOf(),
)