<style scoped>
  body {
    margin: 10;
    padding: 10;
    background-color: #17a2b8;
    height: 100vh;
  }

  .grupa {
    width: 200px;
  }

  #unos {
    margin: auto;
    width: 50%;
    padding: 10px;
    
  }
  #unos .container #unos-row #unos-column #unos-box {
    margin: auto;
    width: 50%;
    margin-top: 10px;
    max-width: 750px;
    height: 750px;
    border: 1px solid #9C9C9C;
    background-color: #EAEAEA;
    font-weight: bold;
  }

  #unos .container #unos-row #unos-column #unos-box #unos-form {
    padding: 20px;
  }

  #unos-link {
    font-weight: normal;
    font-style: italic;
  }
</style>

<template>
  <div id="unos">
    <h2 class="text-center text-white pt-5">{{naslov}}</h2>
    <div class="container">
      <div id="unos-row" class="row justify-content-center align-items-center">
        <div id="unos-column" class="col-md-6">
          <div id="unos-box" class="col-md-12">
            <el-form ref="lijek" :model="lijek" :rules="rules" label-width="140px">
              <h3 class="text-center text-info">{{naslovForme}}</h3>
              <el-form-item label="Sifra:" prop="sifra">
                <div class="grupa">
                <el-input v-model="lijek.sifra"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Naziv:" prop="naziv">
                <div class="grupa">
                <el-input v-model="lijek.naziv"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Vrsta Lijeka:" prop="vrstaLijeka">
                <div class="grupa">
                <el-input v-model="lijek.vrstaLijeka"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Oblik Lijeka:" prop="oblikLijeka">
                <div class="grupa">
                <el-input v-model="lijek.oblikLijeka"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Sastav Lijeka:" prop="sastav">
                <div class="grupa">
                <el-input v-model="lijek.sastav"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Proizvođač:" prop="proizvodjac">
                <div class="grupa">
                <el-input v-model="lijek.proizvodjac" autocomplete="off"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Napomena:" prop="napomena">
                <div class="grupa">
                <el-input v-model="lijek.napomena"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Poeni:" prop="poeni">
                <div class="grupa">
                <el-input  v-model.number="lijek.poeni"></el-input>
                </div>
              </el-form-item>
               <el-form-item label="Unos:" prop="preporuceniUnos">
                <div class="grupa">
                <el-input  v-model="lijek.preporuceniUnos"></el-input>
                </div>
              </el-form-item>
              <el-form-item>
                <div class="grupa">
                  <el-button @click="prviProzor=true">Zamenski Lekovi</el-button>
                </div>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit">Formiraj</el-button>
                <el-button @click="resetForm('lijek')">Obriši</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
    </div>
  </div>


  <el-dialog
    title="Zamenski Lekovi"
    v-model="prviProzor"
    width="30%"
    destroy-on-close
    center>
    <div style="display: flex;
  justify-content: center;  padding-bottom: 20px;">
    </div>
    
   
      <el-table
        ref="multipleTable"
        :data="zamenskiLijekovi"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          property="sifra"
          label="Šifra"
          width="120">
        </el-table-column>
        <el-table-column
          property="naziv"
          label="Naziv"
          width="120">
        </el-table-column>
      </el-table>
      <div style="margin-top: 20px">
        
        <el-button @click="toggleSelection()">Očisti Selekciju</el-button>
      </div>
    

    <template #footer>
      <span class="dialog-footer">     
        <el-button @click="izlaz()">Izadji</el-button>
      </span>
    </template>
    </el-dialog>

</template>

<script>
  export default {
    name: 'FormaLijekova',
    data() {
      var provjeriPoene = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('Unesite broj poena!'));
        }
        setTimeout(() => {
          if (!Number.isInteger(value)) {
            callback(new Error('Unesite broj nenegativan!'));
          } else {
            if (value < 0) {
              callback(new Error('Broj mora biti nenegativan!'));
            } else {
              callback();
            }
          }
        }, 1000);
      };
      return {
        naslov: 'Unos Lijeka',
        naslovForme: 'Unos Lijeka',
        zamenskiLijekovi: [],
        prviProzor: false,
        lijek: {
          sifra: '',
          naziv: '',
          vrstaLijeka: '',
          oblikLijeka: '',
          sastav: '',
          proizvodjac: '',
          napomena: '',
          poeni: '',
          preporuceniUnos: '',
          
        },
        multipleSelection: [],
         rules: {
           sifra: [
             { required: true, message: 'Unesite sifru!', trigger: 'blur' },
           ],
          naziv: [
            { required: true, message: 'Unesite naziv!', trigger: 'blur' },
          ],
          vrstaLijeka: [
            { required: true, message: 'Unesite vrstu!', trigger: 'blur' }
          ],
          oblikLijeka: [
            { required: true, message: 'Unesite oblik!', trigger: 'blur' }
          ],
          sastav: [
            { required: true, message: 'Unesite sastav!', trigger: 'blur' }
          ],
          proizvodjac: [
            { required: true, message: 'Unesite proizvodjaca!', trigger: 'blur' },
          ],
          napomena: [
            { required: true, message: 'Unesite napomenu!', trigger: 'blur' }
          ],
          poeni: [
            { required: true, validator: provjeriPoene, trigger: 'blur' }
          ],
          preporuceniUnos: [
            { required: true, message: 'Unesite preporučen unos!', trigger: 'blur'}
          ]
        }
      };
      
    },
    props: ['izmjeniIndikator'],
    mounted(){
        this.$store.dispatch('APlijekovi/dobaviSveLijekove')
        .then(response => {
            this.zamenskiLijekovi = response.data;
        })
    },
    methods: {
      izlaz(){
        this.prviProzor = false; 
        console.log(this.multipleSelection);
      },
      open1() {
        this.$message({
          showClose: true,
          message: 'Dodat lijek',
          type: 'success'
        });
      },
      onSubmit() {
        var l = {naziv: this.lijek.naziv, sifra: this.lijek.sifra, vrstaLijeka: this.lijek.vrstaLijeka, 
        oblikLijeka: this.lijek.oblikLijeka, sastav: this.lijek.sastav, proizvodjac: this.lijek.proizvodjac,
        napomena: this.lijek.napomena, poeni: this.lijek.poeni, preporuceniUnos: this.lijek.preporuceniUnos, zamenskiLekovi: this.multipleSelection };
        console.log(l);
        this.$store.dispatch('APlijekovi/dodajLijek', l)
        .then(response => {
            this.open1();
            this.izmjeniIndikator(2, 'nesto');
          return response;
        });
        
      },
      resetForm(formName){
        this.$refs[formName].resetFields();
      },
      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
    
    }
  }
</script>