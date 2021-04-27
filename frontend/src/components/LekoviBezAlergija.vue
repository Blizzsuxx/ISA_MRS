<template>
<h2>Lekovi na koje korisnik nije alergican</h2>
  <el-table
    ref="multipleTable1"
    :data="this.$store.state.Alergije.lekoviBezAlergija"
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
      property="vrstaLijeka"
      label="Vrsta lijeka"
      width="120">
    </el-table-column>
    <el-table-column
      property="oblikLijeka"
      label="Oblik lijeka"
      width="120">
    </el-table-column>
    <el-table-column
      property="sastav"
      label="Sastav"
      width="120"
     show-overflow-tooltip>
    </el-table-column>
  </el-table>

  <div style="margin-top: 20px">
    <!-- <el-button @click="toggleSelection([tableData[1], tableData[2]])">Toggle selection status of second and third rows</el-button> -->
    <el-button @click="toggleSelection()">Očisti selekciju</el-button>
    <el-button @click="premesti()">Premesti u alergije</el-button>
  </div>
</template>

<script>
export default {
  name: 'LekoviBezAlergija',
  data() {
      return {
        multipleTable1: []
      }
    },
    
    methods: {
     
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable1.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable1.clearSelection();
        }
      },
      premesti(){
        
        this.$store.dispatch("Alergije/premesti",this.multipleTable1)
       
        this.$refs.multipleTable1.clearSelection();
      },
      handleSelectionChange(val) {
        this.multipleTable1 = val;
      }
    }
  }

</script>
