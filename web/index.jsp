<link rel="stylesheet" href="styles/style1.css" type="text/css"/>

<div class="login-wrap">
  <div class="login-html">
    <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
    <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>

    <div class="login-form">
      <form method="post" action="login.jsp" onsubmit="return validasi_input(this)"> 
        <div class="sign-in-htm">
          <div class="group">
            <label for="user" class="label">Username</label>
            <input type="text" name="username" class="input" value="" id="username"/>
          </div>
          <div class="group">
            <label for="pass" class="label">Password</label>
            <input type="password" id="password" name="password" value="" data-type="password" class="input"/>
          </div>
          <br><br><br>
          <div class="group">
            <input type="submit" class="button" value="Sign In">
          </div>
          <div class="hr"></div>
        </div>
      </form>
      <script type="text/javascript">
        function validasi_input(form) {
          if (form.username.value == "" || form.password.value == "") {
            alert("Semua Kolom harus diisi bos!!");
            form.username.focus();
            return (false);
          }
          return (true);
        }
      </script>
      <form method="post" action="prosesregis.jsp" onsubmit="return validasi_input2(this)"> 
        <div class="sign-up-htm">
          <div class="group">
            <label for="user" class="label">Username</label>
            <input id="username" type="text" class="input" name="username" value="">
          </div>

          <div class="group">
            <label for="pass" class="label">Password</label>
            <input id="password" type="password" class="input" data-type="password" name="password" value="">
          </div>
          <div class="group">
            <label for="pass2" class="label">Confirm Password</label>
            <input id="password2" type="password" class="input" data-type="password" name="password2" value="">
          </div>


          <div class="group">
            <input type="submit" id="submit" class="button" value="Sign Up">
          </div>

          <div class="hr"></div>
          <div class="foot-lnk">
            <label for="tab-1">Already have an account?</label>
          </div>
        </div>

      </form>
      <script type="text/javascript">
        function validasi_input2(form) {
          if (form.username.value == "" || form.password.value == "" || form.password2.value == "") {
            alert("Semua Kolom harus diisi bos!!");
            form.username.focus();
            return (false);
          }
          if (form.password.value != form.password2.value) {
            alert("Password nggak sama bos!!");
            form.password2.focus();
            return (false);
          }
          return (true);
        }
      </script>
    </div>

  </div>
</div>
