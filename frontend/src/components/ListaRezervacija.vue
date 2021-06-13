<template>
<el-main>
<NavMeniZaPacijenta/>
<h2>Lista rezervacija leka</h2>
  <el-table
    ref="multipleTable1"
    :data="this.$store.state.RezervisaniLekovi.rezervisani"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
    sortable
      type="selection"
      width="55"> <!--deo za selekciju-->
    </el-table-column>
    <el-table-column
    sortable
      property="nazivLeka"
      label="Naziv leka"
      width="120">
    </el-table-column>
    <el-table-column
    sortable
      property="vrstaLeka"
      label="Vrsta leka"
      width="120">
    </el-table-column>
    <el-table-column
    sortable
      property="kolicina"
      label="Kolicina"
      width="120">
    </el-table-column>
    <el-table-column
    sortable
      property="nazivApoteke"
      label="Naziv apoteke"
      width="120">
    </el-table-column>
    <el-table-column
    sortable
      property="datumVazenja"
      label="Datum vazenja rezervacije"
      width="120">
    </el-table-column>
    
    <el-table-column
    sortable
      property="cena"
      label="Cena"
      width="120"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column

      label="Odustani">
      <template #default="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">Odustani</el-button>
        
      </template>
    </el-table-column>
  </el-table>
</el-main>
</template>

<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
//import moment from "moment";
export default {
  components : {NavMeniZaPacijenta},
  name: 'ListaRezervacija',
  async mounted(){
     await this.$store.dispatch("RezervisaniLekovi/dobaviRezervacije")
    },
  data() {
      return {
        multipleTable1: []
      }
    },
    
    methods: {
     
       handleEdit(index, row) {
             //todo, smisli sta je row, a sta index, tj prosledi ime apoteke, ime leka, i datum
        //let rez = moment(this.$store.state.RezervisaniLekovi.rezervisani[index].datumVazenja, "dd.MM.yyyy."); 
        let str1=this.$store.state.RezervisaniLekovi.rezervisani[index].datumVazenja.split(".")
        let today1= new Date();
        let novdan=new Date(str1[2],str1[1]-1,str1[0])
        today1.setDate(today1.getDate()+1);
        
        if((novdan-today1)<-24*60*60*1000){
         this.$message({
                type: 'danger',
                message: 'Istekao je termin za odustajanje od rezervacije.'
              });
        }else{
        this.$store.dispatch("RezervisaniLekovi/odustani",index, row)}
      },
     
      handleSelectionChange(val) {
        this.multipleTable1 = val;
      }
    }
  }

</script>
