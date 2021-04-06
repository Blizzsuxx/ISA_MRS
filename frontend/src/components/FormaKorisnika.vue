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
    height: 650px;
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
            <el-form ref="korisnik" :model="korisnik" :rules="rules" label-width="140px">
              <h3 class="text-center text-info">{{naslovForme}}</h3>
              <el-form-item label="Korisnicko ime:" prop="korisnickoIme">
                <div class="grupa">
                <el-input v-model="korisnik.korisnickoIme"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Sifra" prop="sifra">
                <div class="grupa">
                <el-input v-model="korisnik.sifra"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Potvrda Sifre:" prop="potvrdaSifre">
                <div class="grupa">
                <el-input v-model="korisnik.potvrdaSifre"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Ime:" prop="ime">
                <div class="grupa">
                <el-input v-model="korisnik.ime"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Prezime:" prop="prezime">
                <div class="grupa">
                <el-input v-model="korisnik.prezime" autocomplete="off"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Email:" prop="email">
                <div class="grupa">
                <el-input v-model="korisnik.email" autocomplete="off"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Datum:" prop="rodjendan">
                <div class="grupa">
                <el-date-picker type="date" placeholder="Datum rodjenja" v-model="korisnik.rodjendan" style="width: 100%;"></el-date-picker>
                </div>
              </el-form-item>
              <el-form-item label="Uloga:" prop="uloga">
                <div class="grupa">
                  <el-select v-model="korisnik.uloga" @change="promjena()" filterable placeholder="Select">
                    <el-option
                      v-for="item in opcije"
                      :key="item.uloga"
                      :label="item.label"
                      :value="item.uloga">
                    </el-option>
                  </el-select>
                </div>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit('korisnik')">Formiraj</el-button>
                <el-button @click="resetForm('korisnik')">Obriši</el-button>
              </el-form-item>
              
            </el-form>
            <div id="unos-link" class="text-right">
              <a href="#">Korisnici!</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import moment from 'moment'
  export default {
    name: 'FormaApoteke',
    data() {
        var validatePass = (rule, value, callback) => {
            if (value === '') {
            callback(new Error('Unesite sifru!'));
            } else {
            if (this.korisnik.potvrdaSifre !== '') {
                this.$refs.korisnik.validateField('checkPass');
            }
            callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
            callback(new Error('Unesite sifru ponovo!'));
            } else if (value !== this.korisnik.sifra) {
            callback(new Error('Sifre se ne poklapaju!'));
            } else {
            callback();
            }
        };
        return {
            naslov: 'Unos Korisnika',
            naslovForme: 'Unos Korisnika',
            korisnik: {
            korisnickoIme: '',
            sifra: '',
            potvrdaSifre: '',
            ime: '',
            prezime: '',
            rodjendan: '',
            email: '',
            uloga: 'Dermatolog',
            },
            opcije: [{
              uloga: 'Dermatolog',
              label: 'Dermatolog'
            }, {
              uloga: 'Dobavljac',
              label: 'Dobavljac',
            }, {
              uloga: 'Administrator Sistema',
              label: 'Administrator Sistema'
            },
            {
              uloga: 'Administrator Apoteke',
              label: 'Administrator Apoteke'
            }],
        
         rules: {
          korisnickoIme: [
            { required: true, message: 'Unesite korisnicko ime!', trigger: 'blur' },
          ],
          sifra: [
            { validator: validatePass,  trigger: 'blur' }
          ],
          potvrdaSifre: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          ime: [
            { required: true, message: 'Unesite ime!', trigger: 'blur' }
          ],
          prezime: [
            { required: true, message: 'Unesite prezime!', trigger: 'blur' }
          ],
          rodjendan: [
            { required: true, message: 'Unesite datum rodjenja!', trigger: 'blur' },
            { type: 'date', message: 'Mora biti datum' }
          ],
          email: [
            { required: true, message: 'Unesite email adresu!', trigger: 'blur' },
            { type: 'email', message: 'Unesite validnu adresu!', trigger: ['blur', 'change'] }
          ]
        }
      };
      
    },
    methods: {
      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            var k = {korisnickoIme: this.korisnik.korisnickoIme, sifra: this.korisnik.sifra, 
            ime: this.korisnik.ime, prezime: this.korisnik.prezime, rodjendan:moment(String(this.korisnik.rodjendan)).format('YYYY-MM-DD hh:mm'),
            email: this.korisnik.email, uloga: this.korisnik.uloga};
            alert(k.email);
            alert(this.korisnik.uloga);
           
          } else {
            console.log('error submit!!');
            return false;
          }
        });
        
      },
      resetForm(formName){
        this.$refs[formName].resetFields();
      },
      promjena(){
        this.naslovForme = this.korisnik.uloga;
      }
    }
  }
</script>