<?php helper('form'); 
$validation = session('validation'); 
?>

<div id="sousContenu">

    <?php
        echo form_open(site_url('ModificationMdp/validerChangerMdp'));
    ?>

    <div class="corpsForm">

        <p>
            <?= form_label('Nouveau mot de passe*', 'pwdMdp') ?>
            <?= form_password([
                'name' => 'pwdMdp',
                'id' => 'pwdMdp',
                'maxlength' => 45,
                'size' => 15
            ]) ?>
            <?php if (isset($validation) && $validation->hasError('pwdMdp')): ?>
                <span class="erreurSaisie"><?= esc($validation->getError('pwdMdp')) ?></span>
            <?php endif; ?>
        </p>

        <p>
            <?= form_label('Confirmer le mot de passe*', 'pwdMdpConfirm') ?>
            <?= form_password([
                'name' => 'pwdMdpConfirm',
                'id' => 'pwdMdpConfirm',
                'maxlength' => 45,
                'size' => 15
            ]) ?>
            <?php if (isset($validation) && $validation->hasError('pwdMdpConfirm')): ?>
                <span class="erreurSaisie"><?= esc($validation->getError('pwdMdpConfirm')) ?></span>
            <?php endif; ?>
        </p>
    </div>

    <div class="piedForm">
        <p>
            <?= form_submit('btnValider', 'Valider', ['class' => 'bouton']) ?>
            <?= form_reset('btnEffacer', 'Effacer', ['class' => 'bouton']) ?>
        </p>
    </div>

    <?= form_close() ?>

</div>