<template>
        <el-main>
        <NavMeniZaPacijenta/>
        <h2>Lista zakazanih poseta farmaceutu</h2>
        <el-table :data="tableData">
          <el-table-column prop="pocetak" label="Pocinje"
          :formatter="formirajDatum" >
          </el-table-column>
          <el-table-column prop="kraj" label="Zavrsetak"
          :formatter="formirajDatum" >
          </el-table-column>
          <el-table-column prop="radnik.ime" label="Ime " >
          </el-table-column>
      <el-table-column prop="radnik.prezime" label="Prezime" >
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
                  type="danger"
                  @click="handleOtkazi(scope.$index, scope.row)">Otkazi</el-button>
              
            </template>
          </el-table-column>


        </el-table>
      </el-main>
    
</template>


<script>
import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
  export default {
    name: 'APZakazanePoseteFarmaceutu',
    components : {
       NavMeniZaPacijenta
    },

    async mounted(){
      //pozivanje ucitavanja podataka poseta
      await this.$store.dispatch("APPosete/dobaviPoseteFPacijenta")
      //console.log(this.$store.state.APPosete.zakazanePosetePacijenta);
      this.tableData = this.$store.state.APPosete.zakazanePosetePacijenta;
      
    },

    methods: {
     handleOtkazi(index) {
      let str1=this.$store.state.APPosete.zakazanePosetePacijenta[index].pocetak.split(" ")
      let sdan=str1[0].split("-") //godina mesec dan
      let svreme=str1[1].split(":")
        let today1= new Date();
        let novdan=new Date(sdan[0],sdan[1]-1,sdan[2]);
        console.log(novdan)
        novdan.setHours(svreme[0])
        novdan.setMinutes(svreme[1])
        today1.setDate(today1.getDate()+1);
        console.log(novdan)
        console.log(today1)
        console.log(novdan-today1)
        console.log(24*60*60*1000)
        if((novdan-today1)<-24*60*60*1000){
         this.$message({
                type: 'danger',
                message: 'Istekao je termin za otkazivanje.'
              });
        }else{
           if(confirm("da li zelite da otkazete posetu?")){
             this.$store.dispatch("APPosete/otkaziF",index)}
        }
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