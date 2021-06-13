<template>
  <NavAdminApoteke />
  <el-alert
    v-if="uspjesno"
    @close="uspjesno = false"
    title="Uspjesno"
    type="success"
    center
    >Uspješno kreiran slobodan termin.
  </el-alert>
  <el-alert
    v-if="greska"
    @close="greska = false"
    title="Greska"
    type="error"
    center
  >
    {{ poruka }}
  </el-alert>

  <el-row :gutter="20">
    <el-col :span="12">
      <el-row>
        <DermatoloziTabela
          @promjenjena-selekcija="promjenaDermatologa"
          v-bind:dermatolozi="$store.state.Dermatolozi.sviDermatolozi"
        />
      </el-row>
      <el-row>
        <div id="app">
          <el-card class="box-card" v-if="ucitavanjeSlobodniTermini">
            <template #header>
              <div class="card-header">
                <span
                  ><h3 v-if="ucitavanjeRadnoVrijeme">
                    Radno vrijeme:
                    {{
                      $store.state.Dermatolozi.radnoVrijeme
                        .pocetakRadnogVremena
                    }}-{{
                      $store.state.Dermatolozi.radnoVrijeme.krajRadnogVremena
                    }}
                  </h3></span
                >
              </div>
            </template>
            <div
              v-for="slobodanTermin1 in $store.state.Dermatolozi.slobodniTermini"
              :key="slobodanTermin1.id"
              class="text item"
            >
              <p>
                Vrijeme termina:{{ slobodanTermin1.pocetakTermina }}-{{
                  slobodanTermin1.krajTermina
                }}
              </p>
              <p>Cijena termina:{{ slobodanTermin1.cijenaTermina }}</p>
              <hr />
            </div>
          </el-card>
        </div>
      </el-row>
    </el-col>
    <el-col :span="12">
      <div id="app1">
        <el-card
          class="box-card"
          v-if="ucitavanjeSlobodniTermini && ucitavanjeRadnoVrijeme"
        >
          <template #header>
            <div class="card-header">
              <span>Dodaj novi termin</span>
            </div>
          </template>
          <el-form
            label-position="top"
            label-width="100px"
            :model="formLabelAlign"
          >
            <el-form-item label="Cijena">
              <el-input-number
                v-model="slobodanTermin.cijenaTermina"
                @change="handleChange"
                :min="0"
                :max="20000"
              ></el-input-number>
            </el-form-item>
            <el-form-item label="Pocetka termina">
              <el-time-select
                v-model="slobodanTermin.pocetakTerminaTemp"
                :start="rvOd"
                step="00:30"
                :end="rvDo"
                placeholder="Pocetak termina"
              >
              </el-time-select>
            </el-form-item>
            <el-form-item label="Kraj termina">
              <el-time-select
                v-model="slobodanTermin.krajTerminaTemp"
                :start="rvOd"
                step="00:30"
                :end="rvDo"
                placeholder="Kraj termina"
              >
              </el-time-select>
            </el-form-item>

            <el-form-item label="Datum termina">
              <el-date-picker
                v-model="slobodanTermin.datum"
                type="date"
                placeholder="Izaberi datum"
              >
              </el-date-picker>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="kreirajTermin"
                >Kreiraj termin</el-button
              >
            </el-form-item>
          </el-form>
        </el-card>
      </div>
    </el-col>
  </el-row>
</template>

<style scoped>
el-col {
  height: 100%;
}

card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 600px;
}
.poravnanje {
  align-content: center;
  align-items: center;
  text-align: center;
}
#app {
  position: relative;

  display: flex;
  justify-content: center;
  align-items: center;
}
#app1 {
  position: relative;

  display: flex;
  justify-content: center;
  align-items: center;
}
</style>


<script>
import NavAdminApoteke from "./NavAdminApoteke";
import DermatoloziTabela from "./DermatoloziTabela";
import moment from "moment";
export default {
  name: "SlobodanTermin",
  data() {
    return {
      ucitavanjeRadnoVrijeme: false,
      ucitavanjeSlobodniTermini: false,
      selektovaniDermatolog: 0,
      slobodanTermin: {
        pocetakTerminaTemp: "",
        krajTerminaTemp: "",
        pocetakTermina: "",
        krajTermina: "",
        cijenaTermina: 0,
        datum: '',
        id: 0,
      },
      rvOd: "",
      rvDo: "",
      uspjesno: false,
      greska: false,
      poruka: "",
    };
  },
  methods: {
    promjenaDermatologa(val) {
      this.selektovaniDermatolog = val.id;
      this.slobodanTermin.id = val.id;
      this.$store
        .dispatch("Dermatolozi/dobaviRadnoVrijeme", val.id)
        .then(() => {
          this.ucitavanjeRadnoVrijeme = true;
          this.rvOd = this.$store.state.Dermatolozi.radnoVrijeme.pocetakRadnogVremena;
          this.rvDo = this.$store.state.Dermatolozi.radnoVrijeme.krajRadnogVremena;
        })
        .catch((error) => console.log(error));

      this.$store
        .dispatch("Dermatolozi/dobaviSlobodneTermine", val.id)
        .then(() => {
          this.ucitavanjeSlobodniTermini = true;
        })
        .catch((error) => console.log(error));
    },
    kreirajTermin() {
      if (
        this.slobodanTermin.pocetakTerminaTemp === "" ||
        this.slobodanTermin.krajTerminaTemp === ""
      ) {
        this.greska = true;
        this.poruka = "Vrijeme mora biti specificirano";
        return;
      }
      if (this.slobodanTermin.cijenaTermina === 0) {
        this.greska = true;
        this.poruka = "Cijena mora biti veca od nule";
        return;
      }
      let pocetakTermina = moment(moment(this.slobodanTermin.datum).format("YYYY-MM-DD")+" "+this.slobodanTermin.pocetakTerminaTemp,"YYYY-MM-DD HH:mm")
      let krajTermina = moment(moment(this.slobodanTermin.datum).format("YYYY-MM-DD")+" "+this.slobodanTermin.krajTerminaTemp,"YYYY-MM-DD HH:mm");
      this.slobodanTermin.pocetakTermina =  pocetakTermina.format("YYYY-MM-DD HH:mm")
      this.slobodanTermin.krajTermina  =  krajTermina.format("YYYY-MM-DD HH:mm")
      if (pocetakTermina.isAfter(krajTermina)) {
        this.greska = true;
        this.poruka = "Kraj pregleda ne moze biti prije pocetka";
        return;
      }
      let preklapanje = false;
      this.$store.state.Dermatolozi.slobodniTermini.forEach((elem) => {
        if (
          (pocetakTermina.isSameOrAfter(moment(elem.pocetakTermina, "DD-MM-YYYY HH:mm")) &&
            pocetakTermina.isBefore(moment(elem.krajTermina, "DD-MM-YYYY HH:mm"))) ||
          (krajTermina.isAfter(moment(elem.pocetakTermina, "DD-MM-YYYY HH:mm")) &&
            krajTermina.isSameOrBefore(moment(elem.krajTermina, "DD-MM-YYYY HH:mm"))) ||
          (pocetakTermina.isSameOrBefore(
            moment(elem.pocetakTermina, "DD-MM-YYYY HH:mm")
          ) &&
            pocetakTermina.isSameOrBefore(moment(elem.krajTermina, "DD-MM-YYYY HH:mm")) &&
            krajTermina.isAfter(moment(elem.pocetakTermina, "DD-MM-YYYY HH:mm")) &&
            krajTermina.isSameOrAfter(moment(elem.krajTermina, "DD-MM-YYYY HH:mm")))
        ) {
          this.greska = true;
          this.poruka =
            "Slobodni termini se preklapaju. U jednom vremenskom periodu moze da postoji samo jedan slobodan termin";
          preklapanje = true;
        }
      });
      if (preklapanje) {
        return;
      }

      if (pocetakTermina.isSame(krajTermina)) {
        this.greska = true;
        this.poruka = "Pregled ne moze trajati 0 minuta";
        return;
      }

      this.$store
        .dispatch("Dermatolozi/kreirajSlobodanTermin", this.slobodanTermin)
        .then((rezultat) => {
          if (rezultat === 1) {
            this.uspjesno = true;
          } else {
            this.greska = true;
            this.poruka =
              "Greska pri kreiranju slobodnog termina.Pokušajte ponovo za par trenutaka.";
          }
        });
    },
  },
  components: {
    NavAdminApoteke,
    DermatoloziTabela,
  },
  mounted() {
    this.$store.dispatch("Dermatolozi/dobaviDermatologe");
  },
};
</script>