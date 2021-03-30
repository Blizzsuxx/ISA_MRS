<template>
<h2>Alergije na lekove</h2>
  <el-table
    ref="multipleSelection"
    :data="this.$store.state.Alergije.lekoviAlergija"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55"> <!--deo za selekciju-->
    </el-table-column>
    <el-table-column
      property="naziv"
      label="Naziv"
      width="120">
    </el-table-column>
    <el-table-column
      property="vrstaLijeka"
      label="Vrsta leka"
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
    <el-button @click="izbaci()">Izbaci</el-button>
  </div>
</template>

<script>
export default {
  name: 'Alergije',
  data() {
      return {
        multipleSelection: []
      }
    },
    
    methods: {
     
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleSelection.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleSelection.clearSelection();
        }
      },
      izbaci(){
        console.log(this.$store.state.Alergije.lekoviAlergija.length)
        this.$store.dispatch("Alergije/izbaci",this.multipleSelection)
       
        this.$refs.multipleSelection.clearSelection();
        //TODO pitanje
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
    }
  }

</script>
