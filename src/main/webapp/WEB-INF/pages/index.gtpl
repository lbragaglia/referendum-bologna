<!DOCTYPE html>
<html lang="it">
  <head>
    <title>Dove votare al referendum comunale di Bologna del 26 maggio 2013</title>
    
<!--    <link rel="shortcut icon" href="/images/logo_comitato.ico" type="image/png">-->
<!--    <link rel="icon" href="/images/logo_comitato.ico" type="image/png">-->
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" lang="it" content="referendum, comitato articolo 33, bologna, 26 maggio 2013">
    
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/bootstrap-responsive.css" rel="stylesheet">

    <script src="js/jquery-1.7.2.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
        
    <link href="select2-3.3.2/select2.css" rel="stylesheet"/>
    <script src="select2-3.3.2/select2.js"></script>
    <script src="select2-3.3.2/select2_locale_it.js"></script>
  </head>
  <body>
<script>

    function indirizziTrovati(indirizzo) {
        var markup = "<table><tr><td>"+indirizzo.indirizzo+"</td></tr></table>";
        return markup;
    }

    function selezioneIndirizzo(indirizzo) {
    	\$("#numeroSezione").text(indirizzo.numeroSezione);
        \$("#sezione").text(indirizzo.sezione);
        \$("#indirizzoSezione").text(indirizzo.indirizzoSezione);
        var percorsoSezione = "http://maps.google.com/maps?saddr=" + indirizzo.indirizzo + ",Bologna&daddr=" + indirizzo.indirizzoSezione + ",Bologna&hl=it&z=15";
        \$("#linkPercorsoPiedi").attr("href", percorsoSezione + "&dirflg=w");
        \$("#linkPercorsoAuto").attr("href", percorsoSezione + "&dirflg=t");
        \$("#mappa").attr("src", percorsoSezione + "&dirflg=w&output=embed");
        \$("#risultato").removeClass("hide");
        //window.open(percorsoSezione, "percorsoSezione");
        return indirizzo.indirizzo;
    }

</script>

<script id="script_e6">
\$(document).ready(function() {
\$("#e6").select2({
    placeholder: "Cerca un indirizzo",
    minimumInputLength: 2,
    ajax: {
        url: "/lookup",
        dataType: 'json',
        quietMillis: 200,
        data: function (term, page) {
            return {
                q: term
            };
        },
        results: function (data, page) {
            return {results: data.indirizzi};
        }
    },
    initSelection: function(element, callback) {
    },
    formatResult: indirizziTrovati,
    formatSelection: selezioneIndirizzo,
    dropdownCssClass: "bigdrop",
    escapeMarkup: function (m) { return m; }
});
});
</script>

<% if (params.output != "embed") { %>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="http://referendum.articolo33.org/"><img src="http://referendum.articolo33.org/wp-content/themes/Bold Red v1.3/organic_bold_red/images/logo.png" alt="Comitato Articolo 33 – Referendum" width="130" height="15"></a>
          <div class="nav-collapse collapse">
          	<p class="navbar-text">Referendum comunale consultivo di Bologna del 26 maggio 2013</p>
          </div>
        </div>
      </div>
    </div>
<% } %>
	
    <div class="container">
		<div class="hero-unit">
  			<h1>Dove votare?</h1>
  			<p>Inserisci il tuo indirizzo di residenza:</p>
  			<p>
    			<input type="text" class="bigdrop" id="e6" style="width:500px" tabindex="-1">
  			</p>
  			<p id="risultato" class="alert alert-success hide"><small>Sezione numero </small><strong id="numeroSezione" style="font-size:18pt"></strong>
  				<br><i class="icon-ok"></i> <strong id="sezione"></strong>
  				<br><i class="icon-map-marker"></i> <em id="indirizzoSezione"></em>
  				<br><i class="icon-globe"></i> <small>mostra il percorso <a id="linkPercorsoPiedi" href="http://maps.google.com/maps?q=bologna" target="percorsoSezione">a piedi</a> o <a id="linkPercorsoAuto" href="http://maps.google.com/maps?q=bologna" target="percorsoSezione">in auto</a></small>
  				&nbsp;&nbsp;<a href="#avvertenze" role="button" class="btn btn-mini btn-info" data-toggle="modal">Avvertenze</a>
  			</p>
		</div>
    </div>

	<iframe id="mappa"  width="100%" height="400px" src="http://maps.google.com/maps?q=bologna&output=embed">
    </iframe>

	<div id="avvertenze" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="titolo" aria-hidden="true">
	  <div class="modal-header">
	    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	    <h3 id="titolo">Avvertenze</h3>
	  </div>
	  <div class="modal-body">
	    <p>Elaborazione a cura del <a href="http://referendum.articolo33.org/">Comitato Articolo 33</a> dei <a href="http://www.comune.bo.it/referendum">dati forniti dal Comune di Bologna</a>.</p>
	    <p>L'individuazione della posizione di un indirizzo sulla mappa può non essere precisa.</p>
	    <p>Per ovviare a questi errori puoi cliccare sul link "mostra il percorso" che apre la mappa più grande in un'altra finestra, dove è possibile modificare gli indirizzi o spostare i marker sulla mappa nella posizione corretta.</p>
	  </div>
	  <div class="modal-footer">
	    <!-- button class="btn" data-dismiss="modal" aria-hidden="true">Close</button-->
	    <button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">Chiudi</button>
	  </div>
</div>

  </body>
</html>
