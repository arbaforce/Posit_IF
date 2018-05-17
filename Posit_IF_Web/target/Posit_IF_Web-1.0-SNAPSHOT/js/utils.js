
function ShowDialog(message) {
    
    alert(message);
}

function ColorNameToCode(colorName) {
    switch(colorName.toLowerCase()) {
        case 'marron': return 'brown';
        case 'acajou': return '#4C2F27';
        case 'rouge': return 'red';
        case 'bleu': return 'blue';
        case 'noir': return 'black';
        case 'jaune': return 'yellow';
        case 'gris': return 'grey';
        case 'orange': return 'orange';
        case 'violet': return 'purple';
        default: return 'white';
    }
}

function ZodiacSignToCode(zodiacSign){
    switch(zodiacSign.toLowerCase()){
        case 'bélier': return 'Bélier.png';
        case 'taureau': return 'Taureau.png';
        case 'gémeaux': return 'Gémeaux.png';
        case 'cancer': return 'Cancer.png';
        case 'lion': return 'Lion.png';
        case 'vierge': return 'Vierge.png';
        case 'balance': return 'Balance.png';
        case 'scorpion': return 'Scorpion.png';
        case 'sagittaire': return 'Sagittaire.png';
        case 'capricorne': return 'Capricorne.png';
        case 'verseau': return 'Verseau.png';
        case 'poissons': return 'Poissons.png';
        default : return 'notFound.png';
    }
}

function ChineseSignToCode(chineseSign){
    switch(chineseSign.toLowerCase()){
        case 'rat': return 'Rat.png';
        case 'bœuf': return 'Buffle.png';
        case 'tigre': return 'Tiger.png';
        case 'lapin': return 'Lapin.png';
        case 'dragon': return 'Dragon.png';
        case 'serpent': return 'Serpent.png';
        case 'cheval': return 'Cheval.png';
        case 'chèvre': return 'Chèvre.png';
        case 'singe': return 'Singe.png';
        case 'coq': return 'Coq.png';
        case 'chien': return 'Chien.png';
        case 'cochon': return 'Cochon.png';
        default : return 'notFound.png';
    }
}
