<template>
<NavMeniZaPacijenta/>
<span>
<el-col :span="12">
    <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose">
      
        <el-menu-item-group title="Odaberite sta zelite da ocenite">
          <el-menu-item index="1-1" @click="kliknut1">Dermatolog</el-menu-item>
          <el-menu-item index="1-2" @click="kliknut2">Farmaceut</el-menu-item>
          <el-menu-item index="1-3" @click="kliknut3">Apoteka</el-menu-item>
          <el-menu-item index="1-4" @click="kliknut4">Lek</el-menu-item>
        </el-menu-item-group>
    </el-menu>
    </el-col >
    <el-col :span="0">
 <el-table :data="tableData">
          
          <el-table-column prop="id" label="id" 
          >
          </el-table-column>
          <el-table-column prop="naziv" label="Naziv " >
          </el-table-column>
        <el-table-column prop="cena" label="Cena">
          </el-table-column>
        <el-table-column prop="prosecnaOcena" label="Ocena">
          </el-table-column>
          <el-table-column
              align="right">
            <template #default="scope">
              <el-button
                  size="mini"
                  type="info"
                  @click="handleInfo(scope.$index, scope.row)">Upisi ocenu</el-button>
              
              
            </template>
          </el-table-column>


        </el-table>
    </el-col>
    <ModalniProzorOcena  ref="prozor"/>

</span>

</template>
<script>

import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
import ModalniProzorOcena from './modal/ModalniProzorOcena.vue'
export default {
  components: {NavMeniZaPacijenta, ModalniProzorOcena},
  name: 'FormaZaOcenjivanje',
  data() {
      return {
        tableData: []
      }
    },
    
     mounted(){
      //pozivanje ucitavanja podataka poseta
       this.$store.dispatch("Ocene/dobaviSvojeDermatologe").then(()=>{this.tableData=this.$store.state.Ocene.oceneSve;})
      this.tableData = this.$store.state.Ocene.oceneSve;
      
      //console.log(this.tableData)
      console.log("n")
      
    },
    methods: {
      
     async kliknut1(){this.tableData=[]
       await this.$store.dispatch("Ocene/dobaviSvojeDermatologe")
      this.tableData = this.$store.state.Ocene.oceneSve;
      console.log(this.tableData)
      
      },async kliknut2(){this.tableData=[]
       await this.$store.dispatch("Ocene/dobaviSvojeFarmaceute")
      this.tableData = this.$store.state.Ocene.oceneSve;

      console.log(this.tableData)
      if(this.$store.state.Ocene.oceneSve.length==0){
        this.tableData=[];
      }
      
      },async kliknut3(){
      this.tableData=[]
       await this.$store.dispatch("Ocene/dobaviSvojeApoteke")
      this.tableData = this.$store.state.Ocene.oceneSve;
      console.log(this.tableData)
      
      },async kliknut4(){this.tableData=[]
       await this.$store.dispatch("Ocene/dobaviSvojeLekove")
      this.tableData = this.$store.state.Ocene.oceneSve;
      console.log(this.tableData)
      
      },
     
      handleInfo( index,row){
        console.log(index)
        this.$refs.prozor.ocenjivac.id = row.id
        this.$refs.prozor.ocenjivac.ocena = row.prosecnaOcena
        this.$refs.prozor.modalOpen = true;
      
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
    
  
    emits: ['promjenjena-selekcija']
  }
  

</script>
