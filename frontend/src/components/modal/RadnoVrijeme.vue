 <template>
  <teleport to="body">
    <div v-if="modalOpen" id="myModal" class="modal">
      <div class="modal-content">
        <div class="modal-header">
          <span @click="modalOpen = false" class="close">&times;</span>
          <h2>Definisi radno vrijeme dermatologa</h2>
        </div>
        <div class="modal-body">
          <el-form
            :label-position="labelPosition"
            label-width="100px"
            :model="radnoVrijeme"
          >
            <el-form-item label="Pocetak radnog vremena">
              <el-time-picker
                v-model="radnoVrijeme.pocetakRadnogVremena"
              >
              </el-time-picker>
            </el-form-item>
            <el-form-item label="Kraj radnog vremena">
              <el-time-picker
                v-model="radnoVrijeme.krajRadnogVremena"
              >
              </el-time-picker>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="zaposliDermatologa"
                >Zaposli dermatologa</el-button
              >
            </el-form-item>
          </el-form>
        </div>
        <!-- <div class="modal-footer">
      <h3>Modal Footer</h3>
    </div> -->
      </div>
    </div>
  </teleport>
</template>

<script>
export default {
  name: "RadnoVrijeme",
  data:() => {
    return {
      modalOpen: false,
      radnoVrijeme: {
        radnik: -1,
        pocetakRadnogVremena :  (() => {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            return date
          })(),
        krajRadnogVremena :  (() => {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            return date
          })(),
      },
    };
  },
  methods: {
    zaposliDermatologa() {
      this.$store.dispatch("Dermatolozi/zaposliDermatologa",this.radnoVrijeme).then(()=>{
           this.$store.dispatch("Dermatolozi/dobaviNezaposleneDermatologe");
       this.$message({
          showClose: true,
          message: 'Uspjesno ste zaposlili dermatologa u apoteku.',
          type: 'success'
        });
       })
    },
  },
  mount() {},
};
</script>

<style scoped>
.modal {
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  padding-top: 300px; /* Location of the box */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content */
.modal-content {
  position: relative;
  background-color: #fefefe;
  margin: auto;
  padding: 0;
  border: 1px solid #888;
  width: 30%;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  -webkit-animation-name: animatetop;
  -webkit-animation-duration: 0.4s;
  animation-name: animatetop;
  animation-duration: 0.4s;
}

/* Add Animation */
@-webkit-keyframes animatetop {
  from {
    top: -300px;
    opacity: 0;
  }
  to {
    top: 0;
    opacity: 1;
  }
}

@keyframes animatetop {
  from {
    top: -300px;
    opacity: 0;
  }
  to {
    top: 0;
    opacity: 1;
  }
}

/* The Close Button */
.close {
  color: white;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

.modal-header {
  padding: 10px 16px;
  background-color: #409eff;
  color: white;
}

.modal-body {
  padding: 2px 16px;
}

.modal-footer {
  padding: 2px 16px;
  background-color: #5cb85c;
  color: white;
}
</style>