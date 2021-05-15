<template>
    <div id="unos">
        <h2 class="text-center text-white pt-5">Definisanje Loyalty Programa</h2>
        <div class="container">
        <div id="unos-row" class="row justify-content-center align-items-center">
            <div id="unos-column" class="col-md-6">
            <div id="unos-box" class="col-md-12">
                <el-form ref="popust" :model="popust" :rules="rules" label-width="200-px">
                <h3 class="text-center text-info"></h3>
                <el-form-item label="Regular(%)" prop="popustRegular">
                    <el-input v-model.number="popust.popustRegular"></el-input>   
                </el-form-item>
                <el-form-item label="Silver(od)" prop="odSilver"> 
                    <el-input v-model.number="popust.odSilver"></el-input>   
                </el-form-item>
                <el-form-item label="Silver(%)" prop="popustSilver">
                    <el-input v-model.number="popust.popustSilver"></el-input>
                </el-form-item>
                <el-form-item label="Gold(od)" prop="odGold">
                    <el-input v-model.number="popust.odGold"></el-input>
                </el-form-item>
                <el-form-item label="Gold(%)" prop="popustGold">
                    <el-input v-model.number="popust.popustGold"></el-input>
                </el-form-item>
                <el-form-item label="Broj poena (Pregled)" prop="brojPoenaPregleda">
                    <el-input v-model.number="popust.brojPoenaPregleda"></el-input>
                </el-form-item>
                <el-form-item label="Broj poena (Savetovanje)" prop="brojPoenaSavetovanja">
                    <el-input v-model.number="popust.brojPoenaSavetovanja"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="sacuvaj">Sacuvaj Promjene</el-button>

                </el-form-item>
                
                </el-form>
            </div>
            </div>
        </div>
        </div>
    </div> 

</template>

<script>
  export default {
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
        var provjeriProcente = (rule, value, callback) => {
            if (!value) {
            return callback(new Error('Unesite procenat!'));
            }
            setTimeout(() => {
            if (!Number.isInteger(value)) {
                callback(new Error('Unesite broj od 0 do 100!'));
            } else {
                if (value < 0) {
                callback(new Error('Broj mora biti od 0 do 100!'));
                } else if (value > 100) {
                callback(new Error('Broj mora biti od 0 do 100!'));
                } else {
                callback();
                }
            }
            }, 1000);
        };
        
      return {
        name: 'LoyaltyPrograma',
        popust: {
            popustRegular: 0,
            popustSilver: 0,
            odSilver: 0,
            popustGold: 0,
            odGold: 0,
            brojPoenaPregleda: 0,
            brojPoenaSavetovanja: 0,
            odobren: false
        },
        rules: {
          popustRegular: [
            { validator: provjeriProcente, trigger: 'blur' }
          ],
          popustSilver: [
            { validator: provjeriProcente, trigger: 'blur' },
          ],
          odSilver: [
            { validator: provjeriPoene, trigger: 'blur' }
          ],
          popustGold: [
            { validator: provjeriProcente, trigger: 'blur' }
          ],
          odGold: [
            { validator: provjeriPoene, trigger: 'blur' }
          ],
          brojPoenaPregleda: [
            { validator: provjeriPoene, trigger: 'blur' }
          ],
          brojPoenaSavetovanja: [
            { validator: provjeriPoene, trigger: 'blur' }
          ]
        }
      } 
    
    },
    props: [],
    mounted() {
        this.$store.dispatch("LoyaltyPrograma/dobaviPopust").then(response => {
           this.popust = response;
        })
    },
    methods: {
        open1() {
        this.$message({
          showClose: true,
          message: 'Podaci nisu validni',
          type: 'error'
        });
      },
      open2() {
        this.$message({
          showClose: true,
          message: 'Popust je sacuvan!',
          type: 'success'
        });
      },
      sacuvaj(){
          if (this.popust.odSilver < 0 || this.popust.odGold < this.popust.odSilver) {
              this.open1();
          } else {
              var l = {popustRegular: this.popust.popustRegular, popustSilver: this.popust.popustSilver, 
              odSilver: this.popust.odSilver, popustGold: this.popust.popustGold, 
              odGold: this.popust.odGold, brojPoenaPregleda: this.popust.brojPoenaPregleda, 
              brojPoenaSavetovanja: this.popust.brojPoenaSavetovanja, odobren: true};
              this.$store.dispatch('LoyaltyPrograma/sacuvajPopust', l)
                .then(response => {
                    this.open2();
                    return response;
                });
          }
      }
    },
  }
</script>

<style scoped>
#unos {
    margin: auto;
    width: 30%;
    padding: 5px;
    
}
</style>
