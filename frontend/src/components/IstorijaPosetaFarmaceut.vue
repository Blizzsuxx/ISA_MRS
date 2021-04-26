<template>
        
      <el-main>
        <NavMeniZaPacijenta/>

        <h2>Istorija poseta farmaceut</h2>
        <el-table :data="tableData">
          <el-table-column prop="pocetak" label="Pocinje"
          :formatter="formirajDatum" >
          </el-table-column>
          <el-table-column prop="kraj" label="Zavrsetak" 
          :formatter="formirajDatum">
          </el-table-column>
          <el-table-column prop="radnik.firstName" label="Ime" >
          </el-table-column>
      <el-table-column prop="radnik.lastName" label="Prezime" >
          </el-table-column>
          <el-table-column prop="apoteka.ime" label="Apoteka">
          </el-table-column>
        <el-table-column prop="cena" label="Cena">
          </el-table-column>
          <el-table-column
              align="right">
            <template #default="scope">
              <el-button
                  size="mini"
                  type="info"
                  @click="handleInfo(scope.$index, scope.row)">Detalji</el-button>
              
              
            </template>
          </el-table-column>


        </el-table>
      </el-main>
    
</template>


<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
  export default {
    name: 'IstorijaPosetaFarmaceut',
    components : {
       NavMeniZaPacijenta
    },

    async mounted(){
      //pozivanje ucitavanja podataka poseta
      await this.$store.dispatch("APPosete/dobaviIstorijuF")
      this.tableData = this.$store.state.APPosete.istorijaFarmaceut;
      
    },

    methods: {
      handleInfo(index, row) {
      console.log(index, row);
    }, handleOtkazi(index, row) {
      console.log(index, row);
    },
      formirajDatum(row){
        try{
          let podjeljeno = row.pocetak.split("T")
        return podjeljeno.join(" ")
        }
        catch(error){
          return " "
        }
        
      }   
    },
        data() {
      return {
        tableData: this.$store.state.APPosete.zakazanePosetePacijenta,
      }
    }
  };
</script>