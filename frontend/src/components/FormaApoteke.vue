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
    height: 550px;
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
            <el-form ref="apoteka" :model="apoteka" :rules="rules" label-width="140px">
              <h3 class="text-center text-info">{{naslovForme}}</h3>
              <el-form-item label="Id Apoteke:" prop="id">
                <div class="grupa">
                <el-input v-model="apoteka.id"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Naziv Apoteke:" prop="naziv">
                <div class="grupa">
                <el-input v-model="apoteka.naziv"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Mjesto:" prop="mjesto">
                <div class="grupa">
                <el-input v-model="apoteka.mjesto"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Ulica:" prop="ulica">
                <div class="grupa">
                <el-input v-model="apoteka.ulica"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Broj:" prop="broj">
                <div class="grupa">
                <el-input v-model.number="apoteka.broj" autocomplete="off"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Ptt:" prop="ptt">
                <div class="grupa">
                <el-input v-model="apoteka.ptt"></el-input>
                </div>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit">Formiraj</el-button>
                <el-button @click="resetForm('apoteka')">Obriši</el-button>
              </el-form-item>
              
            </el-form>
            <div id="unos-link" class="text-right">
              <a href="#">Apoteke!</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'FormaApoteke',
    data() {
      return {
        naslov: 'Unos Podataka',
        naslovForme: 'Unos Apoteke',
        apoteka: {
          id: '',
          naziv: '',
          mjesto: '',
          ulica: '',
          broj: '',
          ptt: '',
        },
         rules: {
          id: [
            { required: true, message: 'Unesite id!', trigger: 'blur' },
            { min: 1, max: 5, message: 'Unesite duzinu od 1 do 5!', trigger: 'blur' }
          ],
          naziv: [
            { required: true, message: 'Unesite naziv!', trigger: 'blur' }
          ],
          mjesto: [
            { required: true, message: 'Unesite mjesto!', trigger: 'blur' }
          ],
          ulica: [
            { required: true, message: 'Unesite ulicu!', trigger: 'blur' }
          ],
          broj: [
            { required: true, message: 'Unesite broj!', trigger: 'blur' },
            { type: 'number', message: 'Mora biti broj!' }
          ],
          ptt: [
            { required: true, message: 'Unesite ptt!', trigger: 'blur' }
          ]
        }
      };
      
    },
    methods: {
      onSubmit() {
        var ap = {naziv: this.apoteka.naziv, mjesto: this.apoteka.mjesto, 
        ptt: this.apoteka.ptt, ulica: this.apoteka.ulica, broj: this.apoteka.broj};
        this.$store.dispatch('APApoteke/dodajApoteku', ap);
        
      },
      resetForm(formName){
        this.$refs[formName].resetFields();
      }
    }
  }
</script>