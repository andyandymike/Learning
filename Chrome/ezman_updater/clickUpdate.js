update_button = document.querySelector('body > form > table > tbody > tr:nth-child(2) > td > table:nth-child(9) > tbody > tr:nth-child(6) > td:nth-child(1) > input[type="submit"]');
update_button.value='I am changed'
my_form = document.querySelector('body > form');
my_form.submit();