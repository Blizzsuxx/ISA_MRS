<template>
<h2>Sve registrovane apoteke</h2>
<div style="margin-top: 20px">
    <!-- <el-button @click="toggleSelection([tableData[1], tableData[2]])">Toggle selection status of second and third rows</el-button> -->
    <el-input placeholder="Search" v-model="input"></el-input>
    <button v-on:click="pretrazi">Pretrazi</button>
	
  </div>
  <el-table
    ref="multipleTable"
    :data="this.stanjeLekovi"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      property="naziv"
      label="Naziv"
      width="120">
    </el-table-column>
    <el-table-column
      property="vrsta"
      label="Vrsta"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="cena"
      label="Cena"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="kolicina"
      label="Kolicina"
      show-overflow-tooltip>
    </el-table-column>
  </el-table>
 
</template>

const { defineComponent, ref } = Vue;
<script>

   import { defineComponent, ref } from 'vue'
export default defineComponent ({
  setup() {
    return {
      input: ref(''),
      lekovi: []
    }
  },
   data() {
      
      return {
       //lekovi: this.$store.state.APApoteke.sveApoteke,
      }
    },

    name: 'StanjeLekaMalaTabela',
    
    
  })

</script>
