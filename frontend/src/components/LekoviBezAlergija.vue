<template>
<h2>Lekovi na koje korisnik nije alergican</h2>
  <el-table
    ref="multipleTable"
    :data="this.$store.state.Alergije.lekoviBezAlergija"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55"> <!--deo za selekciju-->
    </el-table-column>
    <el-table-column
      property="lijek.naziv"
      label="Naziv"
      width="120">
    </el-table-column>
    <el-table-column
      property="lijek.vrstaLijeka"
      label="Vrsta lijeka"
      width="120">
    </el-table-column>
    <el-table-column
      property="lijek.oblikLijeka"
      label="Oblik lijeka"
      width="120">
    </el-table-column>
    <el-table-column
      property="lijek.sastav"
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
        multipleSelection1: []
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
        
        this.$store.dispatch("Alergije/premesti",this.multipleSelection1)
       
        this.$refs.multipleTable1.clearSelection();
      },
      handleSelectionChange(val) {
        this.multipleSelection1 = val;
      }
    }
  }

</script>
