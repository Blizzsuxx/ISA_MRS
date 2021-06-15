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
    width: 55%;
    margin-top: 10px;
    max-width: 800px;
    height: 1050px;
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
              <el-form-item label="Korisničko ime:" prop="korisnickoIme">
                <div class="grupa">
                <el-input v-model="korisnik.korisnickoIme"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Šifra" prop="sifra">
                <div class="grupa">
                <el-input type="password" v-model="korisnik.sifra"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Potvrda Šifre:" prop="potvrdaSifre">
                <div class="grupa">
                <el-input type="password" v-model="korisnik.potvrdaSifre"></el-input>
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
                <div class="grupa" v-if="indikator">
                  <el-select v-model="korisnik.uloga" @change="promjena()" filterable placeholder="Select">
                    <el-option
                      v-for="item in opcije"
                      :key="item.uloga"
                      :label="item.label"
                      :value="item.uloga">
                    </el-option>
                  </el-select>
                </div>
                <div v-else>
                    <el-input model-value="ROLE_PACIJENT" v-model="korisnik.uloga" readonly></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Broj Telefona:" prop="brojTelefona">
                <div class="grupa">
                <el-input v-model="korisnik.brojTelefona" autocomplete="off"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Ulica:" prop="ulica">
                <div class="grupa">
                <el-input v-model="korisnik.ulica" autocomplete="off"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Broj ulice:" prop="broj">
                <div class="grupa">
                <el-input v-model="korisnik.broj" autocomplete="off"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Mesto:" prop="mesto">
                <div class="grupa">
                <el-input v-model="korisnik.mesto" autocomplete="off"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Ptt:" prop="ptt">
                <div class="grupa">
                <el-input v-model="korisnik.ptt" autocomplete="off"></el-input>
                </div>
              </el-form-item>
              <el-form-item label="Drzava:" prop="drzava">
                <div class="grupa">
                <el-input v-model="korisnik.drzava" autocomplete="off"></el-input>
                </div>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="onSubmit('korisnik')">Formiraj</el-button>
                <el-button @click="resetForm('korisnik')">Obriši</el-button>
              </el-form-item>
              
            </el-form>
            <div id="unos-link" class="text-right" v-if="indikator">
            </div>
            <div id="unos-link" class="text-right" v-else>
              <a href="/ap/prijava">Prijava!</a>
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
    name: 'FormaKorisnika',
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
            indikator: false,
            korisnik: {
            korisnickoIme: '',
            sifra: '',
            potvrdaSifre: '',
            ime: '',
            prezime: '',
            rodjendan: '',
            email: '',
            uloga: 'ROLE_DERMATOLOG',
            },
            opcije: [{
              uloga: 'ROLE_ADMIN_SISTEMA',
              label: 'ROLE_ADMIN_SISTEMA'
            }, {
              uloga: 'ROLE_ADMIN_APOTEKE',
              label: 'ROLE_ADMIN_APOTEKE',
            }, {
              uloga: 'ROLE_DERMATOLOG',
              label: 'ROLE_DERMATOLOG'
            },
            {
              uloga: 'ROLE_DOBAVLJAC',
              label: 'ROLE_DOBAVLJAC'
            },{
              uloga: 'ROLE_FARMACEUT',
              label: 'ROLE_FARMACEUT' 
            },{
              uloga: 'ROLE_PACIJENT',
              label: 'ROLE_PACIJENT'
            }
            ],
        
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
          ],
          ulica: [
            { required: true, message: 'Unesite ulicu!', trigger: 'blur' }
          ],
          broj: [
            { required: true, message: 'Unesite broj!', trigger: 'blur' }
          ],
          mesto: [
            { required: true, message: 'Unesite mesto!', trigger: 'blur' }
          ],
          ptt: [
            { required: true, message: 'Unesite ptt!', trigger: 'blur' }
          ],
          drzava: [
            { required: true, message: 'Unesite drzavu!', trigger: 'blur' }
          ],
          brojTelefona: [
            { required: true, message: 'Unesite broj telefona!', trigger: 'blur' }
          ],
        }
      }; 
    },

    props: ['izmjeniIndikator'],

    mounted () {
      if (localStorage.getItem('user') === null){
        this.indikator = false;
        this.korisnik.uloga = "ROLE_PACIJENT";
      } else {
        this.indikator = true;
      }
    },
    methods: {
      open1() {
        this.$message({
          showClose: true,
          message: 'Uspješno je dodat novi korisnik, potvrda poslata na email.',
          type: 'success'
        });
      },

      onSubmit(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            var k = {korisnickoIme: this.korisnik.korisnickoIme, sifra: this.korisnik.sifra, 
            ime: this.korisnik.ime, prezime: this.korisnik.prezime, rodjendan:moment(String(this.korisnik.rodjendan)).format('YYYY-MM-DD hh:mm'),
            email: this.korisnik.email, uloga: this.korisnik.uloga, 
            ulica: this.korisnik.ulica, broj: this.korisnik.broj, mesto: this.korisnik.mesto, 
            ptt: this.korisnik.ptt, drzava: this.korisnik.drzava, brojTelefona: this.korisnik.brojTelefona};

            this.$store.dispatch('APKorisnici/dodajKorisnika', k)
            .then(response => {
              if (response){
                this.open1();
              }
              if (this.korisnik.uloga !== 'ROLE_PACIJENT') {
                var nesto;
                if (this.korisnik.uloga === 'ROLE_ADMIN_SISTEMA'){
                  nesto = "AS";
                } else if (this.korisnik.uloga === 'ROLE_ADMIN_APOTEKE'){
                  nesto = "AP";
                } else if (this.korisnik.uloga === 'ROLE_DERMATOLOG'){
                  nesto = "Dermatolozi";
                } else if (this.korisnik.uloga === 'ROLE_DOBAVLJAC'){
                  nesto = "Dobavljaci";
                }
                this.izmjeniIndikator(1, nesto);

              } else { // znaci ako je pacijent u pitanju
                this.$router.push('/ap/prijava');
              }
             

            });
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