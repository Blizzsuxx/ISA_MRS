<template>
<h2>Sve registrovane apoteke</h2>
<div style="margin-top: 20px">
    <!-- <el-button @click="toggleSelection([tableData[1], tableData[2]])">Toggle selection status of second and third rows</el-button> -->
    <el-input placeholder="Search" v-model="input"></el-input>
    <button v-on:click="pretrazi">Pretrazi</button>
	
  </div>
  <el-table
    ref="multipleTable"
    :data="this.zaTabelu"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      property="ime"
      label="Naziv"
      width="120">
    </el-table-column>
    <el-table-column
      property="adresa.Mesto"
      label="Mesto"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="adresa.ulica"
      label="Ulica"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="adresa.broj"
      label="Broj"
      show-overflow-tooltip>
    </el-table-column>
   <el-table-column
      property="ocena"
      label="Ocena"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      property="udaljenost"
      label="Udaljenost"
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
      input: ref('')
    }
  },
   data() {
      
      return {
       zaTabelu: this.$store.state.APApoteke.sveApoteke,
      }
    },

    name: 'APPostojeceApoteke',
    methods: {
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
     pretrazi() {
       //ovde ili instalirati onu glupost, koja nzm cemu sluzi, ili poslati beku
       //https://www.npmjs.com/package/vue-input-search
       //https://vuejs.org/v2/guide/forms.html
       //bdw ako stavim : bez () mogu da unesem tekst, ako stavim samo () ne moze ha ha;
        
        if(this.input===""){return;}
        this.zaTabelu = this.$store.state.APApoteke.sveApoteke.filter( (item) => {return item.ime.includes( this.input)} );
     },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
    }
  })

</script>
