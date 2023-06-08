// Função para obter o histórico de veículos
function getHistorico() {
  const historicoStr = localStorage.getItem("historicoVeiculos");
  if (historicoStr) {
    return JSON.parse(historicoStr);
  } else {
    return [];
  }
}

// Função para salvar o histórico de veículos
function saveHistorico(historico) {
  const historicoStr = JSON.stringify(historico);
  localStorage.setItem("historicoVeiculos", historicoStr);
}
