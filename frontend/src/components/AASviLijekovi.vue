<template>
  <h3>Pregled svih lijekova</h3> 
  
  <LijekoviTabela @promjenjena-selekcija="selektujRedove" ref='dijete' v-bind:lijekovi="$store.state.APlijekovi.sviLijekovi" referenca="multipleTable" />
  <div style="margin-top: 20px">
    <el-button type="primary" @click="ocistiSelekciju()" plain>Očisti selekciju</el-button>
    <el-popconfirm 
  title="Da li ste sigurni da želite izbrisati lijek?" @confirm="izbrisiLijek()" confirmButtonText='Ok'
  cancelButtonText='Odustani'
>
<template #reference>
    <el-button type="danger"   plain>Izbrisi lijek</el-button>
   </template>
  </el-popconfirm>
    
    <el-button @click="otvoriProzor" plain type="primary">
        Promjeni cijenu lijeka
    </el-button>
    
    <ModalniProzorCijena  ref="prozor"/>
  </div>
</template>  
 

<script>
import LijekoviTabela from  './LijekoviTabela'
import ModalniProzorCijena from './modal/ModalniProzorCijena'
export default {
  name: 'APMagacinLijekovi',
  data() {
      return {
        greska : false,
        poruka : "",
        prozor: false,
        modalOpen: false
      }
    },
    components:{
      LijekoviTabela,
      ModalniProzorCijena
    },
    methods: {
      ocistiSelekciju(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.dijete.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.dijete.$refs.multipleTable.clearSelection();
        }
      },
      izbrisiLijek(){
          
          if(this.$refs.dijete.multipleSelection.length!=1){
             this.$message({
                type: 'danger',
                message: 'Treba da bude selektovan isključivo jedan lijek za brisanje'
              });
              return;
          }
          this.$store.dispatch("APlijekovi/izbrisiLijekove",this.$refs.dijete.multipleSelection[0]).then(response=>{
            console.log(typeof response.data)
            if(response.data===0){
              this.$message({
                type: 'success',
                message: 'Uspješno izbrisan lijek iz apoteke'
              });
              this.$store.dispatch("APlijekovi/dobaviLijekoveAdmin")
              this.$refs.dijete.$refs.multipleTable.clearSelection();
            }
            else{
              this.$message({
                type: 'info',
                message: 'Lijek koji ste željeli obrisati se nalazi u nekoj od rezervacija.'
              });
            }
          })
        
      },
      otvoriProzor(){

        if(this.$refs.dijete.multipleSelection.length!=1 ){
          this.$message({
                type: 'info',
                message :`Samo jedan lijek treba biti selektovan pri mijenjanju cijene`
              });
          
        }
        else{

        this.$refs.prozor.lijek.cijena = this.$refs.dijete.multipleSelection[0].cijena
        this.$refs.prozor.lijek.id = this.$refs.dijete.multipleSelection[0].id
        this.$refs.prozor.lijek.datumIstekaCijene = new Date(this.$refs.dijete.multipleSelection[0].datumIstekaCijene)
        this.$refs.prozor.modalOpen = true;

        }

      },
      selektujRedove(val) {
        this.$refs.dijete.multipleSelection = val;
      }
    }
  }

</script>