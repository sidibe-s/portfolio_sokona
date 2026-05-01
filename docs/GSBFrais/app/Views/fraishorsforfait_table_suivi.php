<?php
$nbJustificatifs = count($fraishorsforfait);
?>
<p>
    <strong>Descriptif des éléments hors forfait - <?= $nbJustificatifs ?> justificatif<?= $nbJustificatifs > 1 ? 's' : '' ?> reçu<?= $nbJustificatifs > 1 ? 's' : '' ?> -</strong>
</p>

<table class="tbl-frais" style="width: 95%">
    <thead>
        <tr>
            <th>Date</th>
            <th>Libellé</th>
            <th>Montant</th>
            <th>Refuser</th>
            <th>Reporter</th>
        </tr>
    </thead>
    <tbody>
        <?php if (!empty($fraishorsforfait)) : ?>
            <?php foreach ($fraishorsforfait as $frais) : ?>
                <?php 
                    $estRefuse = str_starts_with($frais['libelle'], 'REFUSE:');
                    $estReporte = str_starts_with($frais['libelle'], 'REPORT:');
                ?>
                <tr>
                    <td><?= esc($frais['dateFraisFR']) ?></td>
                    <td><?= esc($frais['libelle']) ?></td>
                    <td class="td-montant"><?= esc($frais['montantFormate']) ?></td>

                    <td class="td-center">
                        <?php if (!$estReporte) : ?>
                            <?php if ($estRefuse) : ?>
                                <span class="icone-refus">✗</span>
                            <?php else : ?>
                                <?= anchor(
                                    'suiviFicheFrais/refuser_fraishorsforfait/' . $frais['idLigneFHF'] . '/' . $idFiche,
                                    '✗',
                                    ['class' => 'btn-refuser']
                                ) ?>
                            <?php endif; ?>
                        <?php else : ?>
                            <span class="btn-reporter-disabled">—</span>
                        <?php endif; ?>
                    </td>

                    <td class="td-center">
                        <?php if (!$estRefuse) : ?>
                            <?php if ($estReporte) : ?>
                                <span class="icone-report">✓</span>
                            <?php else : ?>
                                <?= anchor(
                                    'suiviFicheFrais/reporter_fraishorsforfait/' . $frais['idLigneFHF'] . '/' . $idFiche,
                                    '✓',
                                    ['class' => 'btn-reporter']
                                ) ?>
                            <?php endif; ?>
                        <?php else : ?>
                            <span class="btn-reporter-disabled">—</span>
                        <?php endif; ?>
                    </td>
                </tr>
            <?php endforeach; ?>
        <?php else : ?>
            <tr>
                <td colspan="5">Aucun frais hors forfait</td>
            </tr>
        <?php endif; ?>
    </tbody>
</table>