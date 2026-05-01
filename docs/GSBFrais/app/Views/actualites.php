<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
<style>
<?php include __DIR__ . '/style/mesStyles.css'; ?>
</style>



<?php if (!empty($actualites)): ?>

    <!-- Ancienne version (liste) -->
    <!-- <div>
        <?php foreach ($actualites as $article): ?>
            <div>
                <?php if (!empty($article['image'])): ?>
                    <img src="<?= esc($article['image']) ?>" style="width:200px;">
                <?php endif; ?>
                <h3><?= esc($article['titre']) ?></h3>
                <p><?= esc($article['description']) ?></p>
                <p>Publié le <?= esc($article['date']) ?></p>
                <?php if (!empty($article['lien'])): ?>
                    <a href="<?= esc($article['lien']) ?>" target="_blank">Lire l'article complet</a>
                <?php else: ?>
                    <span>Pas de lien disponible</span>
                <?php endif; ?>
            </div>
        <?php endforeach; ?>
    </div> -->

    <!-- Version Carousel -->
    <div id="carouselActualites" class="carousel slide" data-bs-ride="carousel" data-bs-interval="5000">

        <div class="carousel-inner">
            <?php $isFirst = true; ?>
            <?php foreach ($actualites as $article): ?>
                <div class="carousel-item <?= $isFirst ? 'active' : '' ?>">
                    <?php if (!empty($article['image'])): ?>
                        <img class="d-block w-100"
                             src="<?= esc($article['image']) ?>"
                             alt="<?= esc($article['titre']) ?>">
                    <?php else: ?>
                        <img class="d-block w-100"
                             src="https://via.placeholder.com/1000x500?text=Pas+d'image"
                             alt="Image non disponible">
                    <?php endif; ?>

                    <div class="carousel-caption d-md-block">
                        <h5><?= esc($article['titre']) ?></h5>
                        <p><?= esc($article['description']) ?></p>
                        <small>Publié le <?= esc($article['date']) ?></small><br>
                        <?php if (!empty($article['lien'])): ?>
                            <a href="<?= esc($article['lien']) ?>" target="_blank"
                               class="btn btn-light btn-sm mt-2">
                                Lire l'article
                            </a>
                        <?php endif; ?>
                    </div>
                </div>
                <?php $isFirst = false; ?>
            <?php endforeach; ?>
        </div>

        <!-- Les boutons navigation -->
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselActualites" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Précédent</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselActualites" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Suivant</span>
        </button>

    </div>

<?php else: ?>
    <p class="text-center mt-4">Aucune actualité pour l'instant.</p>
<?php endif; ?>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
