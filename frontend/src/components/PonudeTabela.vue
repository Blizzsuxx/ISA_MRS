
<template>

  <el-table
    :data="ponude"
    style="width: 100%">
    <el-table-column
      prop="id"
      label="Sifra"
      >
    </el-table-column>
    <el-table-column
      prop="nazivPonude"
      label="Naziv ponude"
      >
    </el-table-column>
    <el-table-column
      prop="dobavljac"
      label="Dobavljac"
      >
    </el-table-column>
    <el-table-column
      prop="cijenaPonude"
      label="Cijena ponude"
      >
    </el-table-column>
    <el-table-column
      prop="status"
      label="Status"
      >
    </el-table-column>
    <el-table-column
      fixed="right"
      label="Opcije"
     >
      <template #default="scope">
        <el-button type="primary" plain v-if="scope.row.status===0 && narudzbenica.zavrsena && ! narudzbenica.prihvacena && narudzbenica.pripada" @click="()=>pripremiPonudu(scope.row.id)"  size="small">Potvrdi</el-button>
        <div v-if="scope.row.status===2">
            Odobrena
            </div>
        <div v-if="scope.row.status===1">
            Odbijena
            </div>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog
    title="Cijene novih lijekova"
    v-model="noveCijene"
    width="40%"
    destroy-on-close
    center>
    <div style="display: flex;
  justify-content: center;  padding-bottom: 20px;">
    </div>
<el-table
    height="500"
    :data="noviLijekovi"
    style="width: 100%"
  >
    <el-table-column property="lijek.naziv" label="Naziv"> </el-table-column>
    <el-table-column property="cijena" label="Cijena" >
      <template #default="scope">
        <el-input-number v-model="scope.row.cijena" :min="1" :max="1000000"></el-input-number>
      </template>
       </el-table-column>


  </el-table>
    <template #footer>
      <span class="dialog-footer">
         <el-button @click="prihvatiPonudu">Prihvati</el-button>
        <el-button @click="noveCijene = false">Izadji</el-button>
      </span>
    </template>
    </el-dialog>
</template>

<script>
  export default {
      data() {
      return {
        noveCijene: false,
        nId : this.$route.params.id,
        noviLijekovi:new Array(),
        ponudaId : -1
      }
    },
    methods: {
      pripremiPonudu(id){
        this.ponudaId = id;
        this.$store.dispatch("Ponude/pripremiPonudu",{id: id,nId:this.$route.params.id}).then(()=>{
          this.definisiListu();
        })
         
      },
      prihvatiPonudu(){
          this.$store.dispatch("Ponude/prihvatiPonudu",{id: this.ponudaId,nId:this.$route.params.id,lijekovi:this.noviLijekovi}).then(()=>{
              this.$store.dispatch("Ponude/dobaviPonude",this.nId);
              this.$emit("odobrena-ponuda");
         });
      },
      definisiListu(){
        this.noviLijekovi = new Array()
        let sifre = this.$store.state.Ponude.noviLijekovi;
        console.log(sifre)
        sifre.forEach(element => {
              this.narudzbenica.listaStanja.forEach(element1 => {
                  if(element===element1.lijek.id){
                      this.noviLijekovi.push(element1)
                  }
              });
        });
        console.log(this.noviLijekovi)
        this.noveCijene=true;
      }
    },
    props: ["ponude","narudzbenica"],
    emits: ["odobrena-ponuda"]
  }
</script>

<style>

</style>
