<template>
<h2>Sve registrovane apoteke</h2>
<div style="margin-top: 20px">
    <!-- <el-button @click="toggleSelection([tableData[1], tableData[2]])">Toggle selection status of second and third rows</el-button> -->
    <el-input @input="pretrazi()" ></el-input>
    <el-button @click="removeFromPharmacy()">Pretrazi</el-button>
  </div>
  <el-table
    ref="multipleTable"
    :data="this.$store.state.APApoteke.sveApoteke"
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

<script>
export default {
  name: 'APPostojeceApoteke',
  data() {
      return {
       
        multipleSelection: []
      }
    },
    
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
     pretrazi: {
       //ovde ili instalirati onu glupost, koja nzm cemu sluzi, ili poslati beku
       //https://www.npmjs.com/package/vue-input-search
       //https://vuejs.org/v2/guide/forms.html
       //bdw ako stavim : bez () mogu da unesem tekst, ako stavim samo () ne moze ha ha
     },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
    }
  }

</script>
