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
  <div>
    <div id="map" class="map"></div>
  <div id="unos">
    <h2 class="text-center text-white pt-5">{{naslov}}</h2>
    <div class="container">
      <div id="unos-row" class="row justify-content-center align-items-center">
        <div id="unos-column" class="col-md-6">
          <div id="unos-box" class="col-md-12">
            <el-form ref="apoteka" :model="apoteka" :rules="rules" label-width="140px">
              <h3 class="text-center text-info">{{naslovForme}}</h3>
              <el-form-item label="Naziv Apoteke:" prop="ime">
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
                <el-input v-model="apoteka.broj" autocomplete="off"></el-input>
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
              <a href="/ap/AdministratorSistema">Apoteke!</a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>

  import Map from 'ol/Map'
  import TileLayer from 'ol/layer/Tile'
  import View from 'ol/View'
  import OSM from 'ol/source/OSM'
  import 'ol/ol.css'
  import {fromLonLat} from 'ol/proj';
  import {toLonLat} from 'ol/proj';
  import axios from 'axios'
  export default {
    name: 'FormaApoteke',
    data() {
      return {
        naslov: 'Unos Podataka',
        naslovForme: 'Unos Apoteke',
        apoteka: {
          naziv: '',
          mjesto: '',
          ulica: '',
          broj: '',
          ptt: '',
          sirina: '',
          duzina: ''
        },
         rules: {
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
          ],
          ptt: [
            { required: true, message: 'Unesite ptt!', trigger: 'blur' }
          ]
        }
      };
      
    },
    methods: {
      onSubmit() {
        var ap = {ime: this.apoteka.naziv, mjesto: this.apoteka.mjesto, 
        ptt: this.apoteka.ptt, ulica: this.apoteka.ulica, broj: this.apoteka.broj,duzina:this.apoteka.duzina,sirina:this.apoteka.sirina};
        this.$store.dispatch('APApoteke/dodajApoteku', ap)
        .then(response => {
            this.$message({
                type: 'success',
                message: 'Dodata apoteka'
              });
            this.$router.push('/ap/AdministratorSistema');
            return response;
        });
        
      },
      resetForm(formName){
        this.$refs[formName].resetFields();
      }
    },
    mounted(){

        let map =  new Map({
            target: 'map',
            layers: [
              new TileLayer({
                source: new OSM()
              })
            ],
            view: new View({
              center: fromLonLat([19.83,45.26]),
              zoom: 13
            })
          });
        let vm=this;
        map.on('singleclick', function (evt) {
            
            let coordinate = toLonLat(evt.coordinate, 'EPSG:3857');
            vm.apoteka.duzina = coordinate[0];
            vm.apoteka.sirina = coordinate[1];
            axios.get(`http://nominatim.openstreetmap.org/reverse?format=json&lon=${coordinate[0]}&lat=${coordinate[1]}`)
                        .then(response => 
                        {console.log(response.data)
                        let podatak = "";
                        if(response.data.address.quarter!=undefined){
                            podatak = response.data.address.quarter
                        }
                        else{
                          podatak = response.data.address.neighbourhood
                        }
                        vm.apoteka.mjesto = podatak+","+response.data.address.city
                        vm.apoteka.ptt = response.data.address.postcode
                        vm.apoteka.ulica =  response.data.address.road
                        vm.apoteka.broj = response.data.address.house_number
                        }
                        )
        });
    }
  }
</script>