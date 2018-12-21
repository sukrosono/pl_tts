/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var foods_order = [], drinks_order = [];
function setup() {
  enable_basic_protection();
}

function enable_basic_protection() {
//  hit bonus proteksi
//  hanya pada field nomor meja, demi kemudahan akses
//  karena jika proteksi juga di pasang di field nama, akan memperlambat proses penginputan
//  biasanya pengunjung akan saling tunjuk ataupun butuh waktu lebih untuk menentukan nama siapa yang akan dipakai
  $("input[name='table_number']").change(on_change_listener);
}

function on_change_listener(e) {
  let inputEl= event.target;
  let r = $(inputEl).val();
//  console.log("on change listener return value: "+ Boolean(r));
  if (r) {
    $("button#hitung").prop("disabled", false);
  }
  return r;
}

function update_input() {
  let drinks_order_el = document.querySelector('input[name="drinks_order"]'),
          foods_order_el = document.querySelector('input[name="foods_order"]');
  $(drinks_order_el).val(JSON.stringify(drinks_order));
  $(foods_order_el).val(JSON.stringify(foods_order));
}

function calc_payment() {
  let drinks_order_el = document.querySelector('input[name="drinks_order"]'),
          foods_order_el = document.querySelector('input[name="foods_order"]');
  let actionUrl = $('form[method="post"]').attr('action') + '/calc_payment';
//  console.log("drinks_order", drinks_order);
//  console.log("drinks_order", JSON.stringify(drinks_order));
  update_input();
  $.ajax({
    type: "POST",
    url: actionUrl,
//    contentType: "application/json", // NOT dataType!
//    mimeType: 'application/json',
    dataType: 'json',
    data: {
      foods_order: JSON.stringify(foods_order),
      drinks_order: JSON.stringify(drinks_order)
    },
    success: function (response) {
      if (response['total_price']) {
        if (response['total_price']>0) {
          $("input[name='total_price']").prop('hidden', false);
          $("input[name='total_price']").val(response['total_price']);
//          console.log("now tp is: ", $("input[name='total_price']").val());
          $("input[type='submit']").prop('disabled', false);
        }
      }
    }
  });
}

function addDrink() {
  let el = $(event.target).parent();
  let input = $(el).find('input[type="number"]').eq(0).val(),
          drinkName = $(el).find(".card-title").eq(0).html().trim();
  drinks_order.push({
    name: drinkName,
    qty: input
  });
//  console.log("input: ", input);
//  console.log("drinkName: ", drinkName);
}

function addFood() {
  let el = $(event.target).parent();
  let input = $(el).find('input[type="number"]').eq(0).val(),
          foodName = $(el).find(".card-title").eq(0).html().trim();
  foods_order.push({
    name: foodName,
    qty: input
  });
}

document.onreadystatechange = function () {
  if (document.readyState === 'complete') {
    setup();
  }
}

